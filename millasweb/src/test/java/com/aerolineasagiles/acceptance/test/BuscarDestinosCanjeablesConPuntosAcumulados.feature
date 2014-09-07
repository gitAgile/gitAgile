Feature: Se obtiene una lista de destinos que pueden ser adquiridos con la cantidad de puntos acumulados por el cliente.
 
Scenario: Al consultar los puntos acumulados de un cliente, se obtiene las opciones de destino que pueden ser canjeadas en base a los puntos obtenidos
Given puntos acumulados del cliente igual 1000
When se busca opciones de destinos canjeables con 1000 puntos 
Then se obtiene las opciones Chicago y Bogota
