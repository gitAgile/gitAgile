Feature: Sumar los puntos acumulados por vuelos consumidos para un determinado cliente.
 
Scenario: Al Ingresar la cedula de un cliente se deberia obtener el numero de puntos acumulados por vuelos consumidos
Given una cedula de un cliente 1721220000
When se busca por esa cedula 1721220000
Then se obtiene 500 como cantidad de puntos acumulados