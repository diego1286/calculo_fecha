package com.calculo_fecha.calcullo_fecha.core.interfaces.services;




import com.calculo_fecha.calcullo_fecha.core.interfaces.repositori.IFechaRepository;
import com.calculo_fecha.calcullo_fecha.entidad.Fecha;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FechaService {
    private final IFechaRepository repository;

    public FechaService(IFechaRepository repository) {
        this.repository = repository;
    }

    public List<Fecha> obtenerTodas() {
        return repository.findAll();
    }

    public Fecha guardar(Fecha fecha) {
        return repository.save(fecha);
    }

    public static Date calcularInicioSemanaSanta(int año) {
        int a = año % 19;
        int b = año % 4;
        int c = año % 7;
        int d = (19 * a + 24) % 30;
        int dias = d + (2 * b + 4 * c + 6 * d + 5) % 7;

        int dia = 15 + dias;
        int mes = 3;
        if (dia > 31) {
            dia -= 31;
            mes = 4;
        }
        return new Date(año - 1900, mes - 1, dia);
    }

    public static Date agregarDias(Date fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DATE, dias);
        return calendar.getTime();
    }

    public static Date siguienteLunes(Date fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        int diasFaltantes = (Calendar.MONDAY - diaSemana + 7) % 7;
        return agregarDias(fecha, diasFaltantes);
    }
}