const API_URL = "https://game-list-api-rjqftd4irq-uc.a.run.app/api";

let videojuegos = [];
let categorias = [];
let editandoId = null;

const form = document.getElementById("game-form");
const gamesList = document.getElementById("games-list");
const categoriaSelect = document.getElementById("categoriaId");
const estadoSelect = document.getElementById("estado");

document.addEventListener("DOMContentLoaded", async () => {
  await cargarCategorias();
  await cargarVideojuegos();
});

async function cargarCategorias() {
  const res = await fetch(`${API_URL}/categorias`);
  categorias = await res.json();

  categoriaSelect.innerHTML = '<option value="">-- Categoría --</option>';
  categorias.forEach(c => {
    categoriaSelect.innerHTML += `<option value="${c.id}">${c.nombre}</option>`;
  });

  const filterCat = document.getElementById("filter-category");
  if (filterCat) {
    filterCat.innerHTML = '<option value="">Todas las categorías</option>';
    categorias.forEach(c => {
      filterCat.innerHTML += `<option value="${c.id}">${c.nombre}</option>`;
    });
  }
}

async function cargarVideojuegos() {
  const res = await fetch(`${API_URL}/videojuegos`);
  videojuegos = await res.json();
  renderizar();
}

function buildCover(j) {
  if (j.imagenUrl) {
    return `
      <div class="card-cover">
        <img src="${j.imagenUrl}" alt="${j.titulo}"
             onerror="this.closest('.card-cover').outerHTML='<div class=\\'card-placeholder\\'>🎮</div>'">
      </div>`;
  }
  return `<div class="card-placeholder">🎮</div>`;
}

function renderizar() {
  gamesList.innerHTML = "";

  videojuegos.forEach(j => {
    gamesList.innerHTML += `
      <div class="game-card">
        ${buildCover(j)}
        <div class="card-body">
          <h3>${j.titulo}</h3>
          <p class="card-meta">${j.plataforma?.nombre || 'Sin plataforma'} · ${j.anio}</p>
          <p class="card-categoria">${j.categoria?.nombre || 'Sin categoría'}</p>
          <span class="estado estado-${j.estado}">${j.estado}</span>
          <div class="actions">
            <button class="view-btn" onclick="ver(${j.id})">Ver</button>
            <button class="edit-btn" onclick="editar(${j.id})">Editar</button>
            <button class="delete-btn" onclick="eliminar(${j.id})">Eliminar</button>
          </div>
        </div>
      </div>
    `;
  });
}

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  const data = {
    titulo: document.getElementById("titulo").value,
    anio: Number(document.getElementById("anio").value),
    descripcion: document.getElementById("descripcion")?.value || null,
    imagenUrl: document.getElementById("imagenUrl")?.value || null,
    estado: estadoSelect.value,
    categoria: categoriaSelect.value ? { id: Number(categoriaSelect.value) } : null
  };

  const url = editandoId
    ? `${API_URL}/videojuegos/${editandoId}`
    : `${API_URL}/videojuegos`;

  await fetch(url, {
    method: editandoId ? "PUT" : "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });

  form.reset();
  editandoId = null;
  document.getElementById("form-title").textContent = "Agregar videojuego";
  await cargarVideojuegos();
});

document.getElementById("cancel-btn").addEventListener("click", () => {
  form.reset();
  editandoId = null;
  document.getElementById("form-title").textContent = "Agregar videojuego";
});

function editar(id) {
  const j = videojuegos.find(v => v.id === id);

  document.getElementById("titulo").value = j.titulo;
  document.getElementById("anio").value = j.anio;
  if (document.getElementById("descripcion")) {
    document.getElementById("descripcion").value = j.descripcion || "";
  }
  if (document.getElementById("imagenUrl")) {
    document.getElementById("imagenUrl").value = j.imagenUrl || "";
  }
  categoriaSelect.value = j.categoria?.id || "";
  estadoSelect.value = j.estado;

  editandoId = id;
  document.getElementById("form-title").textContent = "Editar videojuego";
  document.getElementById("game-form").scrollIntoView({ behavior: "smooth" });
}

async function eliminar(id) {
  if (!confirm("¿Eliminar este juego?")) return;
  await fetch(`${API_URL}/videojuegos/${id}`, { method: "DELETE" });
  await cargarVideojuegos();
}

function ver(id) {
  const j = videojuegos.find(v => v.id === id);
  document.getElementById("game-detail").innerHTML = `
    <b>${j.titulo}</b> (${j.anio})<br>
    Plataforma: ${j.plataforma?.nombre || '—'}<br>
    Categoría: ${j.categoria?.nombre || '—'}<br>
    Estado: <span class="estado estado-${j.estado}">${j.estado}</span><br>
    ${j.descripcion ? `<p style="margin-top:8px">${j.descripcion}</p>` : ''}
  `;
}

document.getElementById("reload-btn")?.addEventListener("click", cargarVideojuegos);
