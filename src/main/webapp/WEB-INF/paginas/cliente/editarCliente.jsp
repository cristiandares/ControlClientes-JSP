<%--definimos la libreria jstl de core con la que vamos a trabajar para presentar la informacion --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- fontawesome Iconos -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
        <!-- Bootstrap CSS -->
        <script src="https://kit.fontawesome.com/5dbc759145.js" crossorigin="anonymous"></script>

        <title>Editar Cliente</title>
    </head>
    <body>

        <!--Header -->
        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"/>
        <!--/Header -->

        <form action="${pageContext.request.contextPath}/ServletControlador?accion=modificar&idCliente=${cliente.idCliente}"
              method="post" class="was-validated">

            <!--Botones de Navegacion -->
            <jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEdicion.jsp"/>
            <!--/Botones de Navegacion-->

            <section id="detail">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <div class="card">
                                <div class="card-header">
                                    <h4>Editar Cliente</h4>
                                </div>
                                <div class="card-body">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <labe for="nombre">Nombre
                                                <input type="text" class="form-control" name="nombre" required value="${cliente.nombre}" />
                                            </labe>
                                        </div>
                                        <div class="form-group">
                                            <labe for="apellido">Apellido
                                                <input type="text" class="form-control" name="apellido" required value="${cliente.apellido}" />
                                            </labe>
                                        </div>
                                        <div class="form-group">
                                            <labe for="email">Email
                                                <input type="email" class="form-control" name="email" required value="${cliente.email}" />
                                            </labe>
                                        </div>
                                        <div class="form-group">
                                            <labe for="telefono">Telefono
                                                <input type="text" class="form-control" name="telefono" required value="${cliente.telefono}" />
                                            </labe>
                                        </div>
                                        <div class="form-group">
                                            <labe for="saldo">Saldo
                                                <input type="number" class="form-control" name="saldo" required value="${cliente.saldo}" step="any"/>
                                            </labe>
                                        </div>
                                    </div>
                                </div>                            
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>



        <!--Footer-->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"/>
        <!--/Footer-->


        <!-- Option 2: Separate Popper and Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>

    </body>
</html>