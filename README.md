## 🔧Levantar el proyecto con Android Studio y el Emulador

Una explicacion de como levantar el proyecto con mockmoon para ver su funcionamiento con datos de prueba.

### 🛠 Requisitos

- [Mockoon](https://mockoon.com/download/) instalado y corriendo.
- Android Studio con un emulador configurado.
- Un smartphone con android.

---

###  Pasos

1. **Abrir Mockoon y crea un entorno**  
   - Agrega un nuevo entorno.
   - Agregar un GET con este endpoint: recipeListV1.
   - Agregar el JSON de ejemplo.
   - Verificar el puerto de Mockoon en mi caso era el  `3001`.
   - Si se va a usar el smartphone para probar se tiene que agregar 0.0.0.0.
   - Iniciar el servidor.

2. **Modificar el código **  
   - Obtener la ip de la PC o laptop donde se este ejecutando el código.
   - En la clase NetworkModule cambiar si es necesario la base URL:"http://IP:3001/" dependiendo del puerto de mockoon
   - El enpoint debe coincidir con lo definido en RecipeListClient: @GET("recipeListV1")
   - en network_security_config.xml también se configuro el ip en la linea: <domain includeSubdomains="true">IP</domain>

 3. **JSON de prueba**
    -En mi caso estoy usando este JSON:

    
{
  "recipeList": [
    {
      "id": "4",
      "nombre": "Ceviche",
      "imagen": "https://luchitoscookingclass.com/wp-content/uploads/2024/07/ceviche-peruano-1.jpg",
      "ingredientes": [
        "Pescado fresco",
        "Cebolla roja",
        "Limón",
        "Ají limo",
        "Culantro",
        "Camote",
        "Choclo",
        "Sal"
      ],
      "pasos": [
        "Cortar el pescado fresco en cubos.",
        "Cortar la cebolla en tiras finas.",
        "Mezclar el pescado con el limón y dejar reposar unos minutos.",
        "Agregar la cebolla, ají limo, culantro y sal.",
        "Servir acompañado de camote y choclo."
      ],
      "descripcion": "Plato tradicional peruano a base de pescado fresco marinado en limón."
    },
    {
      "id": "5",
      "nombre": "Lomo Saltado",
      "imagen": "https://www.recetasnestle.com.pe/sites/default/files/srh_recipes/9f7c7c7e2b7b8a0b8f0c7d5c6e9f4a1b.jpg",
      "ingredientes": [
        "Carne de res",
        "Cebolla roja",
        "Tomate",
        "Ají amarillo",
        "Sillao",
        "Vinagre",
        "Papas",
        "Arroz",
        "Culantro"
      ],
      "pasos": [
        "Cortar la carne en tiras.",
        "Cortar la cebolla y el tomate en trozos.",
        "Freír las papas hasta que estén doradas.",
        "Saltear la carne a fuego alto.",
        "Agregar la cebolla, tomate y ají amarillo.",
        "Añadir sillao y vinagre.",
        "Servir acompañado de arroz y papas fritas."
      ],
      "descripcion": "Clásico plato peruano que combina carne salteada con cebolla, tomate y papas fritas."
    },
    {
      "id": "6",
      "nombre": "Ají de Gallina",
      "imagen": "https://www.recetasnestle.com.pe/sites/default/files/srh_recipes/aji-de-gallina.jpg",
      "ingredientes": [
        "Pechuga de pollo",
        "Ají amarillo",
        "Pan",
        "Leche evaporada",
        "Cebolla",
        "Ajo",
        "Queso parmesano",
        "Nueces",
        "Arroz",
        "Papa"
      ],
      "pasos": [
        "Cocinar el pollo y deshilacharlo.",
        "Remojar el pan en leche.",
        "Preparar un aderezo con cebolla, ajo y ají amarillo.",
        "Agregar el pan remojado y cocinar hasta obtener una crema.",
        "Añadir el pollo deshilachado y mezclar.",
        "Agregar queso parmesano y nueces.",
        "Servir acompañado de arroz y papa."
      ],
      "descripcion": "Tradicional preparación peruana cremosa hecha con pollo deshilachado y ají amarillo."
    },
    {
      "id": "7",
      "nombre": "Papa a la Huancaína",
      "imagen": "https://www.recetasnestle.com.pe/sites/default/files/srh_recipes/papa-a-la-huancaina.jpg",
      "ingredientes": [
        "Papa amarilla",
        "Queso fresco",
        "Ají amarillo",
        "Leche evaporada",
        "Galletas saladas",
        "Aceite",
        "Lechuga",
        "Aceitunas negras",
        "Huevo"
      ],
      "pasos": [
        "Cocinar las papas hasta que estén suaves.",
        "Limpiar y cortar el ají amarillo.",
        "Licuar el queso, ají, leche y galletas.",
        "Agregar aceite y continuar licuando hasta obtener una crema.",
        "Cortar las papas en rodajas.",
        "Servir sobre hojas de lechuga y acompañar con la salsa huancaína.",
        "Decorar con huevo y aceitunas."
      ],
      "descripcion": "Entrada peruana preparada con papas cocidas y una cremosa salsa de ají amarillo."
    },
    {
      "id": "8",
      "nombre": "Arroz con Pollo",
      "imagen": "https://www.recetasnestle.com.pe/sites/default/files/srh_recipes/arroz-con-pollo.jpg",
      "ingredientes": [
        "Pollo",
        "Arroz",
        "Culantro",
        "Cebolla",
        "Ajo",
        "Ají amarillo",
        "Arvejas",
        "Zanahoria",
        "Choclo"
      ],
      "pasos": [
        "Preparar un aderezo con cebolla, ajo y ají amarillo.",
        "Agregar el culantro licuado.",
        "Dorar las presas de pollo en el aderezo.",
        "Agregar agua y cocinar el pollo.",
        "Retirar el pollo y agregar el arroz.",
        "Añadir las arvejas, zanahoria y choclo.",
        "Cocinar hasta que el arroz esté listo.",
        "Servir acompañado del pollo."
      ],
      "descripcion": "Plato casero peruano preparado con arroz verde, pollo y verduras."
    },
    {
      "id": "9",
      "nombre": "Causa Limeña",
      "imagen": "https://www.recetasnestle.com.pe/sites/default/files/srh_recipes/causa-limena.jpg",
      "ingredientes": [
        "Papa amarilla",
        "Ají amarillo",
        "Limón",
        "Aceite",
        "Pollo",
        "Mayonesa",
        "Palta",
        "Sal"
      ],
      "pasos": [
        "Cocinar las papas y prensarlas.",
        "Agregar ají amarillo, limón, aceite y sal.",
        "Mezclar hasta obtener una masa uniforme.",
        "Preparar el pollo deshilachado con mayonesa.",
        "Colocar una capa de papa.",
        "Agregar el relleno de pollo y palta.",
        "Cubrir con otra capa de papa.",
        "Refrigerar antes de servir."
      ],
      "descripcion": "Entrada fría peruana elaborada con papa amarilla, ají y limón, generalmente rellena de pollo o atún."
    },
    {
      "id": "10",
      "nombre": "Tallarines Verdes",
      "imagen": "https://www.recetasnestle.com.pe/sites/default/files/srh_recipes/tallarines-verdes.jpg",
      "ingredientes": [
        "Fideos",
        "Albahaca",
        "Espinaca",
        "Queso fresco",
        "Leche evaporada",
        "Ajo",
        "Nueces",
        "Aceite",
        "Sal"
      ],
      "pasos": [
        "Cocinar los fideos en agua con sal.",
        "Lavar la albahaca y la espinaca.",
        "Licuar las hojas con queso, leche, ajo y nueces.",
        "Calentar la salsa en una sartén.",
        "Agregar los fideos cocidos.",
        "Mezclar hasta cubrir completamente los fideos.",
        "Servir caliente."
      ],
      "descripcion": "Pasta al estilo peruano preparada con una cremosa salsa de albahaca y espinaca."
    },
    {
      "id": "11",
      "nombre": "Pollo a la Brasa",
      "imagen": "https://www.recetasnestle.com.pe/sites/default/files/srh_recipes/pollo-a-la-brasa.jpg",
      "ingredientes": [
        "Pollo entero",
        "Cerveza negra",
        "Sillao",
        "Ajo",
        "Comino",
        "Pimienta",
        "Sal",
        "Romero",
        "Papas"
      ],
      "pasos": [
        "Limpiar y preparar el pollo.",
        "Preparar una marinada con sillao, ajo, comino, pimienta y sal.",
        "Marinar el pollo durante varias horas.",
        "Colocar el pollo en el horno o parrilla.",
        "Cocinar hasta que esté dorado y completamente cocido.",
        "Freír las papas.",
        "Servir el pollo acompañado de papas y ensalada."
      ],
      "descripcion": "Uno de los platos más populares del Perú, preparado con pollo marinado y cocinado hasta quedar dorado y jugoso."
    }
  ]
}



