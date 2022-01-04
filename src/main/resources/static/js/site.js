// Call the dataTables jQuery plugin
$(function () {
  if (document.querySelector("#TablaUsuarios") != null) {
    CargarUsuarios();
  }
});

async function CargarUsuarios() {
  const rawResponse = await fetch("api/getusers", {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });

  const content = await rawResponse.json();

  console.log(content);
  let html = "";
  content.forEach((item) => {
    html += `<tr>
              <td>${item.Id}</td>
              <td>${item.Nombre}</td>
              <td>${item.Apellido}</td>
              <td>${item.Edad}</td>
              <td>${new Date(item.FechaNacimiento).toLocaleDateString()}</td>
              <td>
                    <a href="#" class="btn btn-danger btn-circle btn-sm" onclick="EliminarUsuario(${
                      item.Id
                    })">
                    <i class="fas fa-trash"></i>
                    </a>
              </td>
             </tr>`;
  });

  $("#TablaUsuarios tbody").html(html);
  $("#TablaUsuarios").DataTable();
}

async function EliminarUsuario(UserId) {
  if (confirm("¿Desea Eliminar el usuario?")) {
    const rawResponse = await fetch(`api/deleteuser/${UserId}`, {
      method: "DELETE",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
    });

    window.top.location.reload();
  }
}

function ValidarTodo() {
  const ListCampos = document.querySelectorAll(".Validate");
  let ok = true;

  for (let index = 0; index < ListCampos.length; index++) {
    const el = ListCampos[index];
    if (el.value == "") {
      ok = false;
      break;
    }
  }

  return ok;
}

async function RegistrarUsuario() {
  if (!ValidarTodo()) {
    alert("Debe llenar todos los campos");
    return null;
  }
  let dat = {};
  dat.Nombre = document.querySelector("#Nombre").value;
  dat.Apellido = document.querySelector("#Apellido").value;
  dat.Edad = document.querySelector("#Edad").value;
  dat.FechaNacimiento = document.querySelector("#FechaNacimiento").value;

  console.log(JSON.stringify(dat));

  const rawResponse = await fetch("api/adduser", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(dat),
  });

  const content = await rawResponse.json();

  console.log(content);
}
