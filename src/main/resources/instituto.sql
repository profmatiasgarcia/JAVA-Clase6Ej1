-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 18-06-2018 a las 16:35:08
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.1.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `instituto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `IDAlumno` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnosmaterias`
--

CREATE TABLE `alumnosmaterias` (
  `IDAlumno` int(11) NOT NULL,
  `IDMateria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materias`
--

CREATE TABLE `materias` (
  `IDMateria` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `materias`
--

INSERT INTO `materias` (`IDMateria`, `Nombre`) VALUES
(1, 'Programacion I'),
(2, 'Programacion II'),
(3, 'Programacion III');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesores`
--

CREATE TABLE `profesores` (
  `IDProfesor` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `profesores`
--

INSERT INTO `profesores` (`IDProfesor`, `Nombre`, `Apellido`) VALUES
(1, 'Matias', 'Garcia'),
(2, 'Claudio', 'Guecia'),
(3, 'Patricio', 'Chacon'),
(4, 'Daniel', 'Bravo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesoresmaterias`
--

CREATE TABLE `profesoresmaterias` (
  `IDProfesor` int(10) NOT NULL,
  `IDMateria` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`IDAlumno`);

--
-- Indices de la tabla `alumnosmaterias`
--
ALTER TABLE `alumnosmaterias`
  ADD PRIMARY KEY (`IDAlumno`,`IDMateria`),
  ADD KEY `IDAlumno` (`IDAlumno`),
  ADD KEY `IDMateria` (`IDMateria`);

--
-- Indices de la tabla `materias`
--
ALTER TABLE `materias`
  ADD PRIMARY KEY (`IDMateria`);

--
-- Indices de la tabla `profesores`
--
ALTER TABLE `profesores`
  ADD PRIMARY KEY (`IDProfesor`);

--
-- Indices de la tabla `profesoresmaterias`
--
ALTER TABLE `profesoresmaterias`
  ADD PRIMARY KEY (`IDProfesor`,`IDMateria`),
  ADD KEY `IDProfesor` (`IDProfesor`),
  ADD KEY `IDMateria` (`IDMateria`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  MODIFY `IDAlumno` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `materias`
--
ALTER TABLE `materias`
  MODIFY `IDMateria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `profesores`
--
ALTER TABLE `profesores`
  MODIFY `IDProfesor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnosmaterias`
--
ALTER TABLE `alumnosmaterias`
  ADD CONSTRAINT `alumnosmaterias_ibfk_1` FOREIGN KEY (`IDAlumno`) REFERENCES `alumnos` (`IDAlumno`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `alumnosmaterias_ibfk_2` FOREIGN KEY (`IDMateria`) REFERENCES `materias` (`IDMateria`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `profesoresmaterias`
--
ALTER TABLE `profesoresmaterias`
  ADD CONSTRAINT `profesoresmaterias_ibfk_1` FOREIGN KEY (`IDProfesor`) REFERENCES `profesores` (`IDProfesor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `profesoresmaterias_ibfk_2` FOREIGN KEY (`IDMateria`) REFERENCES `materias` (`IDMateria`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
