package com.acme.center.platform.learning.domain.model.valueobjects;


/** RECORD CLASS
 * Clase especial para contener datos.
 * Son inmutables y permiten reducir el código y optimizarlo.
 */

public record StudentPerformanceMetricSet(Integer totalCompletedCourses, Integer totalTutorials) {
    public StudentPerformanceMetricSet(){this(0,0);}

    //reglas de negocio
    public StudentPerformanceMetricSet{
        if (totalTutorials <0){
            throw new IllegalArgumentException("The total of tutorials cannot be less than zero");
        }
        if (totalCompletedCourses <0){
            throw new IllegalArgumentException("The total of completed courses cannot be less than zero");
        }

    }

    //#region METHODS:

    //metodo para agregar el total de cursos completados
    public StudentPerformanceMetricSet incrementTotalCompletedCourses(){
        return new StudentPerformanceMetricSet(totalCompletedCourses +1, totalTutorials);
    }

    public StudentPerformanceMetricSet incrementTotalCompletedTutorials(){
        return new StudentPerformanceMetricSet(totalCompletedCourses, totalTutorials +1);
    }

}
