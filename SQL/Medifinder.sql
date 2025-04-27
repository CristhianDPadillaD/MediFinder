CREATE DATABASE IF NOT EXISTS Medifinder;
USE Medifinder;


CREATE TABLE Farmacia (
    idFarmacia INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150),
    telefono VARCHAR(20)
);

CREATE TABLE RepresentanteFarmacia (
    idRepresentante INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    cedula VARCHAR(20) UNIQUE,
    telefono VARCHAR(20),
    correo VARCHAR(100)
);
CREATE TABLE Departamento (
    idDepartamento INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Municipio (
    idMunicipio INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    idDepartamento INT,
    FOREIGN KEY (idDepartamento) REFERENCES Departamento(idDepartamento)
);
CREATE TABLE SolicitudAfiliacion (
    idSolicitud INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME NOT NULL,
    estado ENUM('ACTIVO', 'INACTIVO') NOT NULL,
    comentario VARCHAR(200),
    idMunicipio INT,
    idFarmacia INT,
    idRepresentante INT,
    FOREIGN KEY (idMunicipio) REFERENCES Municipio(idMunicipio),
    FOREIGN KEY (idFarmacia) REFERENCES Farmacia(idFarmacia),
    FOREIGN KEY (idRepresentante) REFERENCES RepresentanteFarmacia(idRepresentante)
);
CREATE TABLE DocumentoSoporte (
    idDocumento INT PRIMARY KEY AUTO_INCREMENT,
    idSolicitud INT,
    nombreDocumento VARCHAR(100),
    tipoDocumento VARCHAR(50),
    rutaArchivo VARCHAR(200),
    FOREIGN KEY (idSolicitud) REFERENCES SolicitudAfiliacion(idSolicitud)
);

