# Ejercicio28_Lavadero

## Descripción

**Ejercicio28_Lavadero** es un sistema de gestión para una comáñia con multiples mini empresas entre ellas el servicio de lavadero de automóviles, desarrollado como ejercicio académico para la asignatura de Ingeniería del Software. El sistema permite administrar clientes, empleados, vehículos, productos, servicios, proveedores, bodegas, áreas de trabajo, contratos y documentos comerciales, facilitando la operación integral de un lavadero.

## Características principales

- **Gestión de clientes y vehículos:** Registro, edición, búsqueda y eliminación de clientes y sus vehículos.
- **Gestión de empleados y contratos:** Creación, edición y eliminación de empleados, asignación de contratos y cargos.
- **Catálogo de productos y servicios:** Organización de productos y servicios en categorías, con información detallada y control de stock.
- **Gestión de bodegas:** Administración de bodegas y productos almacenados.
- **Gestión de proveedores:** Registro de proveedores, información de pago y productos suministrados.
- **Documentos comerciales:** Generación y manejo de cotizaciones, pedidos, notas de corrección, comprobantes de pago y órdenes de venta.
- **Control de áreas de trabajo y cubículos:** Organización de áreas y cubículos para la prestación de servicios.
- **Consultas y reportes:** Listados y búsquedas de clientes, empleados, productos, servicios, proveedores, documentos y más.
- **Persistencia de datos:** Almacenamiento de la información en archivos JSON.

## Estructura del proyecto

- `src/main/java/co/edu/udc/ejercicio28_lavadero/modelo/entidades/`: Clases de entidades del dominio (Empresa, Cliente, Empleado, Producto, Servicio, etc.).
- `src/main/java/co/edu/udc/ejercicio28_lavadero/Principal.java`: Clase principal con los menús y la lógica de interacción.
- `DB/Empresas/`: Carpeta donde se almacenan los archivos JSON de cada empresa.
- `Diagramas/`: Diagramas UML del sistema.
- `README.md`: Este archivo de documentación.

## Requisitos funcionales principales

- Registrar y gestionar clientes, empleados, vehículos, productos, servicios, proveedores y bodegas.
- Generar y consultar documentos comerciales (cotizaciones, pedidos, notas de corrección, comprobantes de pago, órdenes de venta).
- Consultar el catálogo de productos y servicios, categorías, stock y productos por agotarse.
- Gestionar la cola de vehículos y la asignación de servicios a empleados y cubículos.
- Consultar y reportar información relevante para la operación del lavadero.

## Ejecución

1. Clona el repositorio o descarga el proyecto.
2. Asegúrate de tener Java y Maven instalados.
3. Ejecuta el proyecto desde la clase `Principal.java`.
4. Sigue los menús interactivos para gestionar la información del lavadero.

## Dependencias

- [Gson](https://github.com/google/gson): Para la serialización y deserialización de objetos a JSON.

## Autor

- Kevin Manuel Gómez Rojas

## Créditos

- Proyecto desarrollado como ejercicio para la asignatura de Ingeniería del Software, bajo la tutoría de John Carlos Arrieta Arrieta.

