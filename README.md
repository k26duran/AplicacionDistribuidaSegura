# Aplicación Distribuida Segura

En este laboratorio se desarrollo una aplicación segura que permite el acceso desde el browser o navegador web a la aplicación con el objetivo de garantizar integridad, autorización y autenticación. Ésta aplicación solicita los servicios de otra, estas dos aplicaciones se desarrollaron en Java usando Spark y Maven, publicando los dos servicios en AWS y comunicandolos mediante certificados por el puerto seguro HTTPS.
El estado actual de la aplicación la hace no escalable, es necesario realizar modificaciones para poder añadir nuevas conexiones y funcionalidades hacia nuevos servidores. 
Para que la arquitectura sea escalable se puede utilizar el patrón de diseño Factory para la conexión con otros servidores, de esta forma sólo es añadir más fábricas y no se cambiaría el código principal de la aplicación. También se puede considerar la opción de dividir la aplicación en microservicios, de esta forma si se agrega una nueva funcionalidad sólo es llamar a ese servicio.

El informe de este laboratorio se encuentra en el siguiente documento [Informe] (Documento.pdf)

*Karen Paola Duran Vivas*
*Arquitectura Empresarial*
*Escuela Colombiana de Ingenieria Julio Garavito*