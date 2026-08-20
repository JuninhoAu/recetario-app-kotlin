## 🔧Levantar el proyecto con Android Studio y el Emulador

Una explicacion de como levantar el proyecto con mockmoon para ver su funcionamiento con datos de prueba.

### 🛠 Requisitos

- [Mockoon](https://mockoon.com/download/) instalado y corriendo.
- Android Studio con un emulador configurado.

---

### 📍 Pasos

1. **Abrir Mockoon y crea un entorno**  
   - Agrega un nuevo entorno.
   - Agregar un GET con este endpoint: recipeListV1.
   - Verificar el puerto de Mockoon en mi caso era el  `3001`.

2. **Usar la IP `10.0.2.2` en el código**  
   - El Emulator no puede acceder directamente a `localhost`.
   - En la clase NetworkModule cambiar si es necesario la base URL:"http://10.0.2.2:3001/" dependiendo del puerto de mockoon
   - El enpoint debe coincidir con lo definido en RecipeListClient: @GET("recipeListV1")
   - en network_security_config.xml tambien se configuro el ip en la linea: <domain includeSubdomains="true">10.0.2.2</domain>

 3. **Agregar JSON de prueba**
    -En mi caso estoy usando este JSON:

    
    {
  "recipeList": [
    {
       "id":"4",
      "nombre": "Ceviche",
      "imagen": "https://luchitoscookingclass.com/wp-content/uploads/2024/07/ceviche-peruano-1.jpg",
      "ingredientes": [
        "Lechuga romana",
        "Pescado fresco",
        "Cebolla",
        "limon",
        "papa"
      ],
      "pasos": [
        "Mezclar el pescado con el limon y revolver",
        "Lavar y trocear la lechuga.",
        "Mezclar la lechuga con el pescado y cebolla",
        "Añadir papas"
      ],
      "descripcion": "comida a base de pescado ideal para el verano"
    } ]
}  



