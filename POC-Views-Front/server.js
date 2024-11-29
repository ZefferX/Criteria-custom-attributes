const express = require("express");
const app = express();
const PORT = 3000;

// Servir archivos estáticos
app.use(express.static("public"));

// Iniciar el servidor
app.listen(PORT, () => {
    console.log(`Servidor ejecutándose en http://localhost:${PORT}`);
});