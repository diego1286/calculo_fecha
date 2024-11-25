package com.calculo_fecha.calcullo_fecha.core.interfaces.repositori;

import com.calculo_fecha.calcullo_fecha.entidad.Fecha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFechaRepository extends JpaRepository<Fecha, Long> {

}
