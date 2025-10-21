select e.nombre as 'Nombre del empleado', e.apellido_paterno, d.nombre as 'Nombre del departamento' from empleado e, departamento d WHERE e.departamento_id=d.id;
