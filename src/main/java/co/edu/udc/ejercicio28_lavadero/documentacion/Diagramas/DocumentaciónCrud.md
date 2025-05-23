### Documentación de Métodos CRUD
#### 1. Método `agregar(T objeto)`
- **Propósito**: Añade un nuevo objeto a la colección/base de datos
- **Parámetros**: Objeto del tipo específico de la clase
- **Comportamiento**:
    - Verifica si ya existe un objeto con el mismo código/identificador
    - Si existe, lanza una excepción
    - Si no existe, añade el objeto a la colección

#### 2. Método `buscar(String codigo)`
- **Propósito**: Localiza y retorna un objeto específico
- **Parámetros**: Código o identificador único del objeto
- **Retorno**: Objeto encontrado del tipo específico
- **Excepciones**: Lanza una excepción si no encuentra el objeto

#### 3. Método `editar(T objeto)`
- **Propósito**: Modifica un objeto existente
- **Parámetros**: Objeto actualizado
- **Comportamiento**:
    - Localiza el objeto existente
    - Actualiza sus propiedades
    - Guarda los cambios

- **Excepciones**: Lanza una excepción si el objeto no existe

#### 4. Método `eliminar(String codigo)`
- **Propósito**: Elimina un objeto de la colección
- **Parámetros**: Código del objeto a eliminar
- **Comportamiento**:
    - Busca y elimina el objeto
    - Actualiza la colección

- **Excepciones**: Lanza una excepción si el objeto no existe

#### 5. Método `listarTodo()`
- **Propósito**: Obtiene todos los objetos almacenados
- **Retorno**: ArrayList con todos los objetos
- **Comportamiento**: Lee y retorna la colección completa

#### 6. Método `contar()`
- **Propósito**: Obtiene la cantidad total de objetos
- **Retorno**: Integer con el número total de elementos
- **Comportamiento**: Retorna el tamaño de la colección
