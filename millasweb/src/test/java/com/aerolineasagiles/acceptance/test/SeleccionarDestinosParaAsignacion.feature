Feature: Se actualiza los puntos acumulados al seleccionar y asignar el o los destinos que son alcanzables por los puntos acumulados.
 
Scenario: Se selecciona los destinos que se desea asignar, y los puntos acumulados se actualizan.
Given Los destinos seleccionados son Chicago y Bogota cuyos codigos son 1 y 2 respectivamente.
When el cliente utiliza sus 1000 puntos para canjear los destinos 
Then los puntos acumulados del cliente se actualizan a 150
