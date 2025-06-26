-- Seleccionar base de datos
USE lab_system_db;

-- Eliminar tablas en orden inverso de dependencias
DROP TABLE IF EXISTS resultados;
DROP TABLE IF EXISTS orders_details;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS laboratoristas;
DROP TABLE IF EXISTS medicos;
DROP TABLE IF EXISTS pacientes;
DROP TABLE IF EXISTS usuarios;

-- Crear tabla de usuarios generales
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    dni VARCHAR(15) UNIQUE,
    correo VARCHAR(100) UNIQUE,
    telefono VARCHAR(20),
    fecha_nacimiento DATE,
    contrasena VARCHAR(255),
    tipo ENUM('PACIENTE', 'MEDICO', 'LABORATORISTA', 'ADMIN') NOT NULL,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear tabla pacientes (relación 1 a 1 con usuarios)
CREATE TABLE IF NOT EXISTS pacientes (
    id INT PRIMARY KEY,
    numero_historia_clinica VARCHAR(50) UNIQUE,
    FOREIGN KEY (id) REFERENCES usuarios(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear tabla medicos
CREATE TABLE IF NOT EXISTS medicos (
    id INT PRIMARY KEY,
    especialidad VARCHAR(100),
    colegiatura VARCHAR(50),
    FOREIGN KEY (id) REFERENCES usuarios(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Crear tabla laboratoristas
CREATE TABLE IF NOT EXISTS laboratoristas (
    id INT PRIMARY KEY,
    area_laboratorio VARCHAR(100),
    FOREIGN KEY (id) REFERENCES usuarios(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de órdenes
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_orden VARCHAR(50) UNIQUE NOT NULL,
    id_paciente INT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(50) DEFAULT 'PENDIENTE',
    FOREIGN KEY (id_paciente) REFERENCES pacientes(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de parámetros (opcional, puedes definirlo mejor según tus necesidades)
CREATE TABLE IF NOT EXISTS parametros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de detalles de orden
CREATE TABLE IF NOT EXISTS orders_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_orden INT NOT NULL,
    id_parametro INT NOT NULL,
    FOREIGN KEY (id_orden) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (id_parametro) REFERENCES parametros(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabla de resultados
CREATE TABLE IF NOT EXISTS resultados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    valor_resultado VARCHAR(100),
    observacion TEXT,
    id_orden INT,
    id_parametro INT,
    id_laboratorista INT,
    fecha_resultado DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_orden) REFERENCES orders(id),
    FOREIGN KEY (id_parametro) REFERENCES parametros(id),
    FOREIGN KEY (id_laboratorista) REFERENCES laboratoristas(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insertar usuario administrador por defecto
INSERT INTO usuarios (nombre, apellido, dni, correo, telefono, contrasena, tipo)
VALUES ('Admin', 'Sistema', '00000000', 'admin@lab.com', '999999999', 
        '$2a$10$xLFtBIXGtYvAbRqM95JhYeuNd/h6q5r6mhknU9t.ChkmY8b0F.Q0K', 'ADMIN');

-- Insertar parámetros de ejemplo
INSERT INTO parametros (nombre) VALUES
('Hemoglobina'),
('Glucosa'),
('Colesterol');
