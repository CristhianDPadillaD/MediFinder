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
    apellido varchar(100) NOT NULL,
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
CREATE TABLE Documento (
    idDocumento INT PRIMARY KEY AUTO_INCREMENT,
    idSolicitud INT,
    nombreDocumento VARCHAR(100),
    tipoDocumento VARCHAR(50),
    rutaArchivo VARCHAR(200)
);
CREATE TABLE SolicitudAfiliacion (
    idSolicitud INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME NOT NULL,
    estado ENUM('PENDIENTE','APROBADA', 'RECHAZADA') NOT NULL,
    comentario VARCHAR(200),
    idMunicipio INT,
    idRepresentante INT,
    idDocumento INT,
    FOREIGN KEY (idMunicipio) REFERENCES Municipio(idMunicipio),
    FOREIGN KEY (idRepresentante) REFERENCES RepresentanteFarmacia(idRepresentante),
    FOREIGN KEY (idDocumento) REFERENCES Documento(idDocumento)
);

INSERT INTO Departamento (nombre) VALUES ('Nariño');
INSERT INTO Municipio (nombre, idDepartamento)
VALUES ('Pasto', (SELECT idDepartamento FROM Departamento WHERE nombre = 'Nariño'));
INSERT INTO RepresentanteFarmacia (nombre, apellido, telefono, correo ) VALUES ('Luisa', 'Portilla', '3138196821','Luisa123@gmail.com');



