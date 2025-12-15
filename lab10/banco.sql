-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 15-12-2025 a las 01:58:00
-- Versión del servidor: 8.4.7
-- Versión de PHP: 8.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `banco`
--
CREATE DATABASE IF NOT EXISTS `banco` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `banco`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administradores`
--

DROP TABLE IF EXISTS `administradores`;
CREATE TABLE IF NOT EXISTS `administradores` (
  `idAdministrador` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `dni` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `direccion` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `usuario` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `contrasena` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `estado` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`dni`),
  UNIQUE KEY `idEmpleado` (`idAdministrador`),
  UNIQUE KEY `telefono` (`telefono`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `usuario` (`usuario`),
  UNIQUE KEY `idAdministrador` (`idAdministrador`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `administradores`
--

INSERT INTO `administradores` (`idAdministrador`, `dni`, `nombre`, `direccion`, `telefono`, `email`, `usuario`, `contrasena`, `estado`) VALUES
('AD34628123', '34628123', 'Atuncito', 'Lima - San Isidro', '963852741', 'at@gmail.com', 'atun', 'shh', '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `idCliente` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dni` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `direccion` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cuentas` text COLLATE utf8mb4_unicode_ci,
  `telefono` varchar(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `usuario` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contrasena` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estado` tinyint(1) NOT NULL,
  PRIMARY KEY (`dni`),
  UNIQUE KEY `idCliente` (`idCliente`),
  UNIQUE KEY `telefono` (`telefono`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `usuario` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`idCliente`, `dni`, `nombre`, `direccion`, `cuentas`, `telefono`, `email`, `usuario`, `contrasena`, `estado`) VALUES
('CL60060857', '60060857', 'Brayan Motta', 'Socabaya - Arequipa', 'C001', '945846028', 'brayan@gmail.com', 'bmotta', 'CHOCA', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
CREATE TABLE IF NOT EXISTS `cuentas` (
  `numero` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tipo` varchar(7) COLLATE utf8mb4_unicode_ci NOT NULL,
  `saldo` float NOT NULL,
  `titulares` text COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`numero`, `tipo`, `saldo`, `titulares`) VALUES
('C1', 'Ahorros', 6000, 'CL60060857');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE IF NOT EXISTS `empleados` (
  `idEmpleado` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dni` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `direccion` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefono` varchar(9) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `usuario` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `contrasena` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `estado` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`dni`),
  UNIQUE KEY `idEmpleado` (`idEmpleado`),
  UNIQUE KEY `telefono` (`telefono`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `usuario` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`idEmpleado`, `dni`, `nombre`, `direccion`, `telefono`, `email`, `usuario`, `contrasena`, `estado`) VALUES
('EM61761556', '61761556', 'Diego Cahuana', 'Yura - Arequipa', '997134305', 'dcauhana@banco.com', 'DegoX', 'Camargo', '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

DROP TABLE IF EXISTS `transacciones`;
CREATE TABLE IF NOT EXISTS `transacciones` (
  `idTransaccion` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fechaHora` varchar(23) COLLATE utf8mb4_unicode_ci NOT NULL,
  `monto` float NOT NULL,
  `empleado` tinyint(1) NOT NULL,
  `idEncargado` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `idCuenta` varchar(4) COLLATE utf8mb4_unicode_ci NOT NULL,
  `idCliente` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `canal` varchar(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idTransaccion`),
  UNIQUE KEY `fechaHora` (`fechaHora`),
  KEY `idCuenta` (`idCuenta`),
  KEY `idCliente` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `transacciones`
--

INSERT INTO `transacciones` (`idTransaccion`, `fechaHora`, `monto`, `empleado`, `idEncargado`, `idCuenta`, `idCliente`, `canal`) VALUES
('D-2025-12-14-20:34:38.308', '2025-12-14-20:34:38.310', 5000, 0, 'AD34628123', 'C1', 'CL60060857', NULL);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD CONSTRAINT `transacciones_ibfk_1` FOREIGN KEY (`idCuenta`) REFERENCES `cuentas` (`numero`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transacciones_ibfk_2` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
