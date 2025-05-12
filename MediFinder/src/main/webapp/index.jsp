<%-- 
    Document   : index
    Created on : 26/04/2025, 5:09:55 p. m.
    Author     : Cris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido a MediFinder</title>
    <link rel="stylesheet" href="Style/bienvenida.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="icon" href="img/logo.png" type="image/png">
</head>
<body class="bienvenida-body">
    <div class="bubble-background">
        <div class="bubble"></div>
        <div class="bubble"></div>
        <div class="bubble"></div>
        <div class="bubble"></div>
        <div class="bubble"></div>
        <div class="bubble"></div>
        <div class="bubble"></div>
    </div>

    <div class="bienvenida-container">
        <div class="header-container">
            <img src="img/logo.png" alt="Logo MediFinder" class="logo-bienvenida">
            <div class="titulo-container">
                <h1 class="titulo-bienvenida">Bienvenido a MediFinder</h1>
                <p class="subtitulo-bienvenida">Innovamos el acceso a la salud <i class="fa-solid fa-heart pulse" style="color: #ff6b81;"></i></p>
            </div>
        </div>
        
        <div class="botones-bienvenida">
            <button class="btn-registro" onclick="window.location.href='registro.jsp'">
                <i class="fa-solid fa-user-plus"></i> Me quiero registrar
            </button>
            <button class="btn-info" onclick="mostrarInfo()">
                <i class="fa-solid fa-circle-info"></i> Saber más
            </button>
        </div>
        
        <div id="info" class="info-oculta">
            <div class="card-container">
                <div class="info-card">
                    <div class="card-icon">
                        <i class="fa-solid fa-users"></i>
                    </div>
                    <h2>¿Quiénes somos? <span class="emoji">🤍</span></h2>
                    <p>En MediFinder somos una plataforma innovadora dedicada a conectar farmacias y centros médicos, ofreciendo soluciones tecnológicas que optimizan procesos de afiliación y registro</p>
                </div>
                
                <div class="info-card">
                    <div class="card-icon">
                        <i class="fa-solid fa-bullseye"></i>
                    </div>
                    <h2>Nuestra Misión <span class="emoji">🩵</span></h2>
                    <p>Ser el puente confiable entre farmacias y la innovación digital.</p>
                </div>
                
                <div class="info-card">
                    <div class="card-icon">
                        <i class="fa-solid fa-binoculars"></i>
                    </div>
                    <h2>Nuestra Visión <span class="emoji">🤍</span></h2>
                    <p>Convertirnos en la red de farmacias más grande y confiable en nuestro Pais mediante tecnología de punta y un servicio de excelencia</p>
                </div>
            </div>
        </div>
    </div>

    <footer class="footer">
        <p>&copy; Ingenieria de Software - MediFinder - Transformando el acceso a la salud</p>
    </footer>

    <script>
        function mostrarInfo() {
            document.getElementById('info').classList.toggle('visible');
            
            // Scroll suave hacia la sección de información
            if (document.getElementById('info').classList.contains('visible')) {
                setTimeout(() => {
                    document.getElementById('info').scrollIntoView({behavior: 'smooth'});
                }, 300);
            }
        }
    </script>
    
</body>
</html>
