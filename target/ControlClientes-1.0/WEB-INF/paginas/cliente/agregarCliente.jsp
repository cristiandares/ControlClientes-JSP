<div class="modal fade" id="agregarClienteModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Agregar Cliente</h5>
                <button class="close" data-dismiss="modal">
                    <span>&times;</span>
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar"
                  method="post" class="was-validated">
                
                <div class="modal-body">
                    <div class="form-group">
                        <labe for="nombre">Nombre
                            <input type="text" class="form-control" name="nombre" required />
                        </labe>
                    </div>
                    <div class="form-group">
                        <labe for="apellido">Apellido
                            <input type="text" class="form-control" name="apellido" required />
                        </labe>
                    </div>
                    <div class="form-group">
                        <labe for="email">Email
                            <input type="email" class="form-control" name="email" required />
                        </labe>
                    </div>
                    <div class="form-group">
                        <labe for="telefono">Telefono
                            <input type="text" class="form-control" name="telefono" required />
                        </labe>
                    </div>
                    <div class="form-group">
                        <labe for="saldo">Saldo
                            <input type="number" class="form-control" name="saldo" required step="any"/>
                        </labe>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit">Guardar</button>
                </div>                
            </form>
        </div>
    </div>
</div>