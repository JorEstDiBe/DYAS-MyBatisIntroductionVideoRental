CREATE DATABASE rentas;
USE rentas;

CREATE TABLE IF NOT EXISTS VI_CLIENTES (
  documento BIGINT NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  telefono VARCHAR(20) NOT NULL,
  direccion VARCHAR(150) NOT NULL,
  email VARCHAR(150) NOT NULL,
  vetado TINYINT NOT NULL
);

CREATE TABLE IF NOT EXISTS VI_TIPOITEM (
  id INT NOT NULL,
  descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS VI_ITEMS (
  id INT NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  descripcion VARCHAR(500) NOT NULL,
  fechalanzamiento DATE NOT NULL,
  tarifaxdia BIGINT NOT NULL,
  formatorenta VARCHAR(20) NOT NULL,
  genero VARCHAR(20) NOT NULL,
  TIPOITEM_id INT NOT NULL
);

CREATE TABLE IF NOT EXISTS VI_ITEMRENTADO (
  CLIENTES_documento INTEGER NOT NULL,
  ITEMS_id INTEGER NOT NULL,
  fechainiciorenta DATE NOT NULL,
  fechafinrenta DATE NOT NULL
);

-- Para la tabla VI_CLIENTES
INSERT INTO VI_CLIENTES (documento, nombre, telefono, direccion, email, vetado) VALUES
(123456789, 'Juan Perez', '1234567890', 'Calle 123', 'juan@example.com', 0),
(987654321, 'Maria Gomez', '9876543210', 'Avenida 456', 'maria@example.com', 1),
(555555555, 'Pedro Rodriguez', '5555555555', 'Carrera 789', 'pedro@example.com', 0);

-- Para la tabla VI_TIPOITEM
INSERT INTO VI_TIPOITEM (id, descripcion) VALUES
(1, 'Electrónico'),
(2, 'Mueble'),
(3, 'Herramienta');

-- Para la tabla VI_ITEMS
INSERT INTO VI_ITEMS (id, nombre, descripcion, fechalanzamiento, tarifaxdia, formatorenta, genero, TIPOITEM_id) VALUES
(1, 'Televisor', 'Televisor de 50 pulgadas', '2023-01-01', 5000, 'Diario', 'Electrodoméstico', 1),
(2, 'Sofá', 'Sofá de tres puestos', '2022-12-01', 8000, 'Diario', 'Mueble', 2),
(3, 'Martillo', 'Martillo de carpintero', '2023-02-15', 2000, 'Diario', 'Herramienta', 3);

-- Para la tabla VI_ITEMRENTADO
INSERT INTO VI_ITEMRENTADO (CLIENTES_documento, ITEMS_id, fechainiciorenta, fechafinrenta) VALUES
(123456789, 1, '2024-03-15', '2024-03-20'),
(987654321, 2, '2024-03-10', '2024-03-18'),
(555555555, 3, '2024-03-12', '2024-03-17');