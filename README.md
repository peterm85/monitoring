# Monitoriza tus sistemas

*Read this document in [english](https://github.com/peterm85/monitoring/blob/master/README_en.md)*

## ¿Qué vamos a ver?

¿Alguna vez te has encontrado analizando un escurridizo ‘bug’ mientras buceabas entre interminables líneas de log?. ¿Has tenido que relacionar cronológicamente eventos de diferentes servicios y te has sentido identificado con el siguiente personaje?

<img src="doc/thinking.jpg" alt="Thinking"/>

Tranquilo, no estás solo.

Este proceso, además de complejo, puede requerir mucho tiempo y a la larga resulta bastante frustrante. Y si a esto sumamos el estrés causado cuando el ‘bug’ proviene del entorno de producción... ¡Faltaría más!.
¿Pero, en serio no hay ninguna solución para esto? Cuando un proyecto crece, ¿las empresas (grandes y pequeñas) no tienen más alternativas que aumentar los recursos dedicados a este tipo de tareas?
¿Y si te dijera que existen herramientas que además de hacerte la vida más fácil te aportan información extra muy útil para el control de tus aplicaciones?, ¿te gustaría tener acceso a un panel en el que de un vistazo puedas identificar cualquier problema?. Vamos a aprender paso a paso a convertir tu aplicación, de una forma rápida y práctica, en un sistema monitorizado eficaz.

## Contexto

En cualquier software es primordial conocer qué ocurre en todo momento y detectar cuándo y porqué algo no funciona correctamente. Tanto en aplicaciones de escritorio como en arquitecturas monolíticas, el proceso siempre ha sido tan ‘sencillo’ como revisar una consola o algún fichero de log. En arquitecturas orientadas a servicios, la cosa comenzaba a complicarse, dado que debíamos realizar esta tarea por cada uno de los servicios que lo componían. Para arquitecturas de microservicios el problema se dispara dados los aspectos de efimeridad, escalabilidad y modularidad. Por ello, se hace imprescindible un sistema centralizado con el que sea posible coleccionar, analizar y relacionar datos del sistema que permita interpretar el funcionamiento o estado de nuestra aplicación y nos facilite la detección de errores.

## Conceptos

A continuación vamos a aclarar algunos conceptos importantes:

### Diferencia entre Observabilidad y Monitorización

Seguramente ya has escuchado hablar sobre estos dos conceptos y quizás no has sabido cuál es la diferencia. Vamos a identificar los matices:
- **Observabilidad**: según [wikipedia](https://es.wikipedia.org/wiki/Observabilidad) podría definirse como “medición que determina cómo los estados internos de un sistema pueden ser deducidos a través de las salidas externas”.
- **Monitorización**: acción de seguimiento de estas señales o salidas externas para evaluar el estado o salud del sistema.
Quizás pueda parecer algo similar. Lo veremos más fácil con un ejemplo. Si lo trasladamos al mundo de la medicina diríamos que si un paciente (sistema observable) no facilita a su doctor (sistema de monitorización) sus síntomas, alimentación, conductas, etc. (señales o salidas externas) éste no podrá diagnosticar con eficacia qué le está ocurriendo.

<img src="doc/doctor.png" alt="Doctor"/>

*«La monitorización te dice si el sistema funciona. La observabilidad te permite preguntar por qué no funciona.»*

Baron Schwartz

### Los tres pilares de la observabilidad
Como vimos en el apartado anterior, es de gran importancia que nuestro sistema tenga un adecuado grado de observabilidad. A continuación describiremos brevemente las principales señales utilizadas para conseguirlo:

#### Logs
Registros temporales de eventos. Se trata del componente básico de trazabilidad de un sistema. Permite almacenar en un fichero la información, estados y valores de contexto de un momento determinado.

*Paciente: “Doctor, en los últimos meses no me encuentro bien. Me siento hinchado y me duele la tripa con frecuencia.”*

#### Métricas
Las métricas son una representación numérica de datos medidos en intervalos de tiempo. Permiten recopilar una serie de valores de diferentes conceptos que nos facilitan la realización de consultas más precisas.

*Paciente: “Aquí le traigo los resultados del análisis de sangre que me pidió.”*

#### Trazas
Identifican el flujo de eventos en un sistema distribuido. Permite distinguir los componentes/servicios involucrados en un proceso así como las condiciones y efectos que se han ido cumpliendo hasta llegar a la situación final.

*Paciente: “El pasado domingo me encontraba genial. Salí a desayunar a una cafetería donde hacen unos croissants riquísimos y al momento comenzó el dolor de estómago.”*

*Doctor: “Querido Paciente, es probable que usted sea intolerante al gluten.”*

Cuantos más datos tengamos sobre un sistema, más fácil será detectar la causa de un problema. Estará en manos de los arquitectos de sistemas, dependiendo de la complejidad del caso, determinar cuántas señales serán suficientes y su grado de detalle. 

## Índice:

- [Logs](https://github.com/peterm85/monitoring/logging#README.md)
- Métricas
- Trazas
