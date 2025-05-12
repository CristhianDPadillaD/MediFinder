<%-- 
    Document   : registro
    Created on : 11/05/2025, 7:24:35 p. m.
    Author     : Cris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>Registro | MediFinder</title>
        <link rel="stylesheet" href="Style/medifynder.css" />
        <link rel="icon" href="img//logo.png" type="image/png" />
    </head>
    <body class="registro-body">
        <div class="bubble-background">
            <div class="bubble"></div>
            <div class="bubble"></div>
            <div class="bubble"></div>
            <div class="bubble"></div>
            <div class="bubble"></div>
        </div>

        <div class="form-container">
            <img src="img/logo.png" alt="Logo MediFinder" class="logo-registro" />
            <h1>Formulario de Registro</h1>

            <div class="progress-bar-container">
                <div id="progress-bar" class="progress-bar"></div>
            </div>

            <form id="registroForm" action="SvAgregarSolicitud" method="POST" enctype="multipart/form-data" oninput="actualizarProgreso()" onsubmit="return validarFormulario()">
                <div class="form-grid">
                    <div class="form-column">
                        <input type="text" id="nombre"name="nombre" placeholder="Nombre de la Farmacia" required>
                        <select id="departamento" name="departamento" required>
                            <option >Departamento</option>
                            <option value="Narinio">Nariño</option>
                            <option>Poner</option>
                            <option>Poner</option>
                        </select>
                        <input type="text" id="direccion" name="direccion"placeholder="Dirección" required>
                        <input type="email" id="correo" name="correo"placeholder="Correo electrónico" required>
                        <input type="text" id="nit" name="nit"placeholder="NIT" required>
                        <select id="ciudad" name="ciudad"required>
                            <option value="">Ciudad</option>
                            <option value="Pasto">Pasto</option>
                            <option>Ipiales</option>
                            <option>Tuquerres</option>
                        </select>
                    </div>
                    <div class="form-column" >
                        <input type="tel" id="telefono" name="telefono"placeholder="Teléfono" required>
                        <select id="representanteLegal" name="representanteLegal"required>
                            <option value="">Representante Legal</option>
                            <option value = "Luisa maria">Luisa Maria Rivadeneria</option>
                        </select>
                        <label for="camara">Registro Cámara de Comercio</label>
                        <input type="file" id="camara" name="camara"required>
                        <label for="licencia">Licencia de Funcionamiento</label>
                        <input type="file" id="licencia" name="licencia"required>
                        <label for="registroSanitario">Registro Sanitario</label>
                        <input type="file" id="registroSanitario"name="registroSanitario" required>
                        <input type="text" id="comentario" name="comentario" placeholder="Comentarios adicionales">
                    </div>
                </div>
                <div class="buttons-container">
                    <button type="submit">Registrar</button>
                    <button type="button" class="volver" onclick="window.location.href = 'index.jsp'">Volver</button>
                </div>
            </form>
        </div>

        <div id="mensajeFlotante" class="mensaje-flotante"></div>

        <script>
            function actualizarProgreso() {
                const totalCampos = 10;
                let completados = 0;

                const campos = [
                    'nombre', 'departamento', 'direccion', 'correo',
                    'nit', 'ciudad', 'telefono', 'repre',
                    'camara', 'licencia', 'registroSanitario'
                ];

                campos.forEach(id => {
                    const el = document.getElementById(id);
                    if (el && ((el.type === 'file' && el.files.length > 0) ||
                            (el.type !== 'file' && el.value.trim() !== ''))) {
                        completados++;
                    }
                });

                let progreso = (completados / totalCampos) * 100;
                document.getElementById('progress-bar').style.width = progreso + '%';

                if (progreso === 100) {
                    mostrarMensaje("Formulario completo y listo para enviar!");
                }
            }

            function validarFormulario() {
                let valido = true;

                mostrarMensaje("Formulario enviado con éxito!!!"); // Este mensaje debería mostrarse solo si la validación pasa (o siempre si lo prefieres)
                return valido;
            }

            function mostrarMensaje(mensaje) {
                const divMensaje = document.getElementById("mensajeFlotante");
                divMensaje.innerText = mensaje;
                divMensaje.classList.add("mostrar");
                setTimeout(() => divMensaje.classList.remove("mostrar"), 6000);
            }


            window.onload = actualizarProgreso;
        </script>
    </body>
</html>
