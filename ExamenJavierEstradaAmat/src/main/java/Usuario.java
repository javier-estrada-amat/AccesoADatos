import java.io.Serializable;

class Usuario implements Serializable {
        private int id;
        private String nombre;
        private String apellidos;
        private String correo;
        private String password;

        public Usuario(int id, String nombre, String apellidos, String correo, String password) {
            this.id = id;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.correo = correo;
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellidos() {
            return apellidos;
        }

        public String getCorreo() {
            return correo;
        }

        public String getPassword() {
            return password;
        }
    }

