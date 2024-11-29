// Elementos del DOM
const filterList = document.getElementById("filter-list");
const resultsBody = document.getElementById("results-body");
const searchField = document.getElementById("search-field");
const orderByField = document.getElementById("order-by");
const resultsHeader = document.getElementById("results-header");

// Estado de paginación
let currentPage = 0;
const pageSize = 5; // Número de resultados por página
let totalPages = 1;

// Elementos del DOM relacionados con la paginación
const prevPageButton = document.getElementById("prev-page");
const nextPageButton = document.getElementById("next-page");
const pageInfo = document.getElementById("page-info");

// Configuración de campos y encabezados para cada categoría
const categoryConfig = {
  "user-permission": {
    fields: [
      { value: "userId", label: "ID del Usuario" },
      { value: "userName", label: "Nombre del Usuario" },
      { value: "permissionId", label: "ID del Permiso" },
      { value: "permissionName", label: "Nombre del Permiso" },
    ],
    headers: [
      "ID",
      "ID Usuario",
      "Nombre Usuario",
      "ID Permiso",
      "Nombre Permiso",
    ],
  },
  "user-office": {
    fields: [
      { value: "userId", label: "ID del Usuario" },
      { value: "userName", label: "Nombre del Usuario" },
      { value: "officeId", label: "ID de la Oficina" },
      { value: "officeName", label: "Nombre de la Oficina" },
    ],
    headers: [
      "ID",
      "ID Usuario",
      "Nombre Usuario",
      "ID Oficina",
      "Nombre Oficina",
    ],
  },
};

// Función para actualizar campos y encabezados según la categoría seleccionada
function updateCategoryFields(category) {
  const config = categoryConfig[category];

  // Actualizar los campos del filtro ("Campo")
  searchField.innerHTML = config.fields
    .map((field) => `<option value="${field.value}">${field.label}</option>`)
    .join("");

  // Actualizar los campos de orden ("Ordenar por", solo IDs)
  orderByField.innerHTML = config.fields
    .filter((field) => field.value.toLowerCase().includes("id")) // Filtrar solo los campos relacionados con IDs
    .map((field) => `<option value="${field.value}">${field.label}</option>`)
    .join("");

  // Actualizar encabezados de la tabla
  resultsHeader.innerHTML = `
      <tr>
        ${config.headers.map((header) => `<th>${header}</th>`).join("")}
      </tr>
    `;
}

// Evento para cambiar la categoría seleccionada
document
  .getElementById("search-category")
  .addEventListener("change", (event) => {
    const category = event.target.value;
    updateCategoryFields(category);
  });

// Inicializar la configuración de la categoría por defecto
updateCategoryFields("user-permission");

// Función para agregar filtros
document.getElementById("add-filter").addEventListener("click", () => {
  const field = searchField.value;
  const operator = document.getElementById("operator").value;
  const value = document.getElementById("search-value").value;

  if (field && operator && value) {
    const filterItem = document.createElement("div");
    filterItem.textContent = `${field} ${operator} ${value}`;
    filterItem.dataset.filter = JSON.stringify({ field, operator, value });
    filterItem.classList.add("filter-item");
    filterItem.innerHTML += ` <button class="remove-filter">X</button>`;
    filterList.appendChild(filterItem);

    // Añadir funcionalidad para borrar un filtro individual
    filterItem.querySelector(".remove-filter").addEventListener("click", () => {
      filterItem.remove();
    });
  } else {
    alert("Por favor complete todos los campos.");
  }
});

// Función para borrar todos los filtros
document.getElementById("clear-filters").addEventListener("click", () => {
  // Vaciar el contenedor de filtros
  filterList.innerHTML = "";
});

// Función para habilitar/deshabilitar los botones de paginación
function updatePaginationControls() {
  prevPageButton.disabled = currentPage <= 0;
  nextPageButton.disabled = currentPage >= totalPages - 1;
  pageInfo.textContent = `Página ${currentPage + 1} de ${totalPages}`;
}

// Función para aplicar filtros con paginación
async function applyFilters() {
  const filters = [];
  filterList.querySelectorAll(".filter-item").forEach((filterItem) => {
    filters.push(JSON.parse(filterItem.dataset.filter));
  });

  // Obtener la categoría seleccionada
  const category = document.getElementById("search-category").value;

  // Obtener el ordenamiento
  const orderBy = document.getElementById("order-by").value;
  const order = document.getElementById("order").value;

  // Construir la URL de la solicitud
  let apiUrl = `http://localhost:9500/api/${category}/generic-filter?`; // Usa la categoría seleccionada en la URL
  filters.forEach((filter, index) => {
    apiUrl += `filters%5B${index}%5D%5Bfield%5D=${encodeURIComponent(
      filter.field
    )}&`;
    apiUrl += `filters%5B${index}%5D%5Boperator%5D=${encodeURIComponent(
      filter.operator
    )}&`;
    apiUrl += `filters%5B${index}%5D%5Bvalue%5D=${encodeURIComponent(
      filter.value
    )}&`;
  });
  apiUrl += `page=${currentPage}&size=${pageSize}&`;
  if (orderBy)
    apiUrl += `sort=${encodeURIComponent(orderBy)},${encodeURIComponent(
      order
    )}`;

  console.log("URL generada:", apiUrl); // Para depuración

  try {
    const response = await fetch(apiUrl);
    if (response.ok) {
      const data = await response.json();
      const results = data.content;
      totalPages = data.totalPages;

      // Actualizar resultados en la tabla
      resultsBody.innerHTML = results
        .map(
          (result) => `
            <tr>
              <td>${result.id || "N/A"}</td>
              <td>${result.userId || result.USER_ID || "N/A"}</td>
              <td>${result.userName || result.USER_NAME || "N/A"}</td>
              <td>${result.officeId || result.permissionId || "N/A"}</td>
              <td>${result.officeName || result.permissionName || "N/A"}</td>
            </tr>
          `
        )
        .join("");

      // Actualizar controles de paginación
      updatePaginationControls();
    } else {
      resultsBody.innerHTML = `<tr><td colspan="5">Error: ${response.status}</td></tr>`;
    }
  } catch (error) {
    console.error("Error al aplicar filtros:", error);
    resultsBody.innerHTML = `<tr><td colspan="5">Error al aplicar filtros</td></tr>`;
  }
}

// Manejar clic en el botón "Aplicar Filtros"
document.getElementById("apply-filters").addEventListener("click", () => {
  currentPage = 0; // Reiniciar a la primera página
  applyFilters();
});

// Manejar clic en el botón "Anterior"
prevPageButton.addEventListener("click", () => {
  if (currentPage > 0) {
    currentPage--;
    applyFilters();
  }
});

// Manejar clic en el botón "Siguiente"
nextPageButton.addEventListener("click", () => {
  if (currentPage < totalPages - 1) {
    currentPage++;
    applyFilters();
  }
});
