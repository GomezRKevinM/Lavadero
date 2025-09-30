package co.edu.udc.ejercicio28_lavadero.events;

import co.edu.udc.ejercicio28_lavadero.services.ValidarDocumento;

import java.time.Instant;

public record DocumentoValido(String id, Instant fechaValidacion){}