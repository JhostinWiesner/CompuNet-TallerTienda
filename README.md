# Proyecto TiendaTech - Taller de Sockets y Multithreading

Este proyecto implementa un sistema de compras en tiempo real utilizando una arquitectura Cliente-Servidor sobre TCP.

## Estructura del proyecto
```text
app/
├── src/main/java/
│   └── com.tiendatech/
│       ├── modelo/
│       │   └── Producto.java       (Compartida por ambos)
│       ├── servidor/
│       │   ├── ServidorTienda.java
│       │   ├── Inventario.java     (Singleton)
│       │   └── ClienteHandler.java (Runnable para el pool)
│       └── cliente/
│           └── ClienteTienda.java
└── build.gradle.kts
```

## Estructura del Sistema

### 1. Comunicación (Protocolo de Texto)
En lugar de serializar objetos, utilizaremos comandos de texto simples. Esto permite que el sistema sea fácil de depurar:
* **Cliente envía**: `LISTAR` -> **Servidor responde**: Lista de productos.
* **Cliente envía**: `COMPRAR|ID|CANTIDAD` -> **Servidor responde**: `OK` o `ERROR|MOTIVO`.

### 2. Manejo de Concurrencia (Thread Pool)
Hemos implementado un `ExecutorService` con un pool de hilos fijo. 
* **¿Por qué?**: Crear un `new Thread()` por cada cliente es costoso para la memoria. El pool reutiliza hilos existentes, mejorando la estabilidad del servidor ante múltiples conexiones simultáneas.

### 3. Sincronización (Hilo-Seguro)
Para evitar **Condiciones de Carrera** (por ejemplo, que dos clientes compren el último artículo al mismo tiempo), utilizamos:
* `Collections.synchronizedList`: Para proteger la estructura de la lista.
* Bloques `synchronized`: En el método de compra para asegurar que la validación del stock y la resta sean una **operación atómica**.

### 4. Patrones de Diseño Aplicados
* **Singleton**: La clase `Inventario` garantiza que todos los hilos del servidor accedan a la misma fuente de datos.
* **Separación de Responsabilidades**: 
    * `ClienteHandler`: Se encarga exclusivamente de la comunicación (I/O).
    * `Inventario`: Se encarga de la lógica de los productos.

## Cómo ejecutarlo
1.  Ejecutar primero `ServidorTienda.java`.
2.  Ejecutar tantas instancias de `ClienteTienda.java` como se desee.
3.  Seguir las instrucciones del menú en la consola del cliente.