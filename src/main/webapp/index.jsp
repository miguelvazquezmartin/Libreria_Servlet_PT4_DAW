<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LibrerÌa Los Mil Libros</title>
    <!-- Enlace a los estilos de Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <!-- Estilos personalizados -->
    <style>
      /* Fuente personalizada para el t√≠tulo */
      @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@500&display=swap');

      /* Estilos para el cuerpo de la p√°gina */
      body {
        background-image: url('https://images.unsplash.com/photo-1508060793788-7d5f1c40c4ba?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80');
        background-size: cover;
        background-position: center;
      }

      /* Estilos para el encabezado de la tarjeta */
      .card-header {
        background-color: #8B0000;
        color: #fff;
        font-family: 'Roboto', sans-serif;
        border-top-right-radius: 50px;
        border-top-left-radius: 50px;
      }

      /* Estilos para el formulario de inicio de sesi√≥n */
      .form-control {
        border-radius: 25px;
        font-size: 16px;
      }

      .form-group label {
        font-size: 18px;
      }

      .btn-primary {
        background-color: #8B0000;
        border-color: #8B0000;
        border-radius: 25px;
        font-size: 16px;
        padding: 8px 30px;
      }

      .btn-primary:hover {
        background-color: #b71c1c;
        border-color: #b71c1c;
        color: #fff;
      }

      .card-body{
        border-bottom-right-radius: 50px;
        border-bottom-left-radius: 50px;
      }
    </style>
  </head>
  <body>
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              <h3 class="text-center">Entra en nuestra biblioteca</h3>
            </div>
            <div class="card-body">
              <form method="get" action="login">
                <div class="form-group">
                  <label for="username">Usuario:</label>
                  <input type="text" class="form-control" id="username" name="usuario">
                </div>
                <div class="form-group">
                  <label for="password">ContraseÒa:</label>
                  <input type="password" class="form-control" id="password" name="password">
                </div>
                <div class="text-center">
                  <input type="submit" class="btn btn-primary" value="Iniciar sesiÛn"></input>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>

