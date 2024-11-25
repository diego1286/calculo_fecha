package com.calculo_fecha.calcullo_fecha.presentacion;



import com.calculo_fecha.calcullo_fecha.core.interfaces.services.FechaService;
import com.calculo_fecha.calcullo_fecha.entidad.Fecha;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/fechas")
public class FechaController {
    private final FechaService service;

    public FechaController(FechaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Fecha> obtenerFechas() {
        return service.obtenerTodas();
    }

    @PostMapping("/semana-santa")
    public Fecha calcularSemanaSanta(@RequestParam int anio) {
        Date inicioSemanaSanta = FechaService.calcularInicioSemanaSanta(anio);
        Fecha fecha = new Fecha();
        fecha.setFecha(inicioSemanaSanta);
        fecha.setDescripcion("Inicio de Semana Santa del año " + anio);
        return service.guardar(fecha);
    }

    @PostMapping("/siguiente-lunes")
    public Fecha calcularSiguienteLunes(@RequestBody Fecha fecha) {
        Date siguienteLunes = FechaService.siguienteLunes(fecha.getFecha());
        Fecha nuevaFecha = new Fecha();
        nuevaFecha.setFecha(siguienteLunes);
        nuevaFecha.setDescripcion("Siguiente lunes desde " + fecha.getFecha());
        return service.guardar(nuevaFecha);
    }
}