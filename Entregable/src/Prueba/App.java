package Prueba;
public class App 
{
    public static void main(String[] args) throws Exception {
        //hacer que el programa corra las funcionalidades de la aplicacion
        //que cargue las tablas de la base de datos con las funciones de la clase MyConnection
        //que permita al usuario interactuar con la aplicacion
        //que permita al usuario realizar compras de criptomonedas
        //que permita al usuario ver el estado de su cartera
		
    	while(true) {
        System.out.println("Ingrese 1 si quiere utilizar el servicio de Activos");
        System.out.println("Ingrese 2 si quiere utilizar el servicio de Moneda y su Stock");
        System.out.println("Ingrese 3 si quiere utilizar el servicio de Compra y Swap");
        System.out.println("Ingrese cualquier otro numero para salir");
        
        int respuesta = Interfaz.pedirInt();
        int segunda;
        switch(respuesta) 
		{
        case 1:
        		ServicioActivos servicioActivos = new ServicioActivos();
        		System.out.println("Ingrese 1 si quiere visualizar todos los activos cripto");
				System.out.println("Ingrese 2 si quiere visualizar todos los activos fiat");
                System.out.println("Ingrese 3 si quiere generar un activo");
                segunda= Interfaz.pedirInt();
                switch(segunda) {
                	case 1:
                		servicioActivos.mostrarActivosCripto();
                	break;
					case 2:
						servicioActivos.mostrarActivosFiat();
                	case 3:
                		servicioActivos.ingresarActivo();
                	break;
                }
        	break;
        case 2:
        	ServicioMoneda servicioMoneda = new ServicioMoneda();
        	System.out.println("Ingrese 1 si quiere crear una moneda");
        	System.out.println("Ingrese 2 si quiere imprimir las monedas ordenadas");
        	System.out.println("Ingrese 3 si quiere imprimir el Stock ordenada");
        	segunda= Interfaz.pedirInt();
        	switch(segunda) {
        	case 1:
        		servicioMoneda.crearMonedaDesdeUsuario();
        		break;
        	case 2:
        		servicioMoneda.imprimirMonedasOrdenado();
        		break;
        	case 3:
        		servicioMoneda.imprimirStockOrdenado();
            break;
        	}
        	break;
        case 3:	
        	System.out.println("Ingrese 1 si quiere efectuar una compra");
        	System.out.println("Ingrese 2 si quiere efectuar un swap");
        	segunda = Interfaz.pedirInt();
			switch(segunda) {
			case 1:
        	ServicioCompra servicioCompra= new ServicioCompra();
        	
        	servicioCompra.comprar();
            	break;
            	default:
            	return;
			case 2:
				ServicioSwap servicioSwap = new ServicioSwap();
				servicioSwap.swap();
				break;
			}
		default:
				return;
			}
        }
    	}
    }

