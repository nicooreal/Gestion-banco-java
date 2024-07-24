package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Dominio.Cliente;
import Dominio.Localidad;
import Dominio.Pais;
import Dominio.Provincia;
import Dominio.Telefono;
import Dominio.Usuario;

public class ClienteDao implements iClienteDao{
	
	private static final String buscarConId ="SELECT clientes.*,telefonos.id_telefono, telefonos.telefono1, telefonos.telefono2 " + "FROM clientes " + "LEFT JOIN telefonos ON clientes.id_cliente = telefonos.id_cliente " +  "WHERE clientes.id_cliente = ?"; 
	private static final String insertCliente = "INSERT INTO `bd_banco`.`clientes`(DNI, CUIL, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, localidad, provincia, correo_electronico, estado) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String deleteCliente = "DELETE FROM `bd_banco`.`clientes` WHERE id_cliente = ?;";
    private static final String selectAll = "SELECT  clientes.id_cliente, clientes.DNI, clientes.CUIL, clientes.nombre,  clientes.apellido, clientes.sexo, clientes.nacionalidad, clientes.fecha_nacimiento, clientes.direccion, clientes.localidad,  clientes.provincia, clientes.correo_electronico, clientes.estado,  telefonos.id_telefono, telefonos.telefono1, telefonos.telefono2 FROM  clientes LEFT JOIN  telefonos ON clientes.id_cliente = telefonos.id_cliente;";
    private static final String bajaLogica = "UPDATE clientes SET estado = 'false' WHERE id_cliente = ?;";
    private static final String updateCliente = "UPDATE clientes SET DNI = ?, CUIL = ?, nombre = ?, apellido = ?, sexo = ?, nacionalidad = ?, fecha_nacimiento = ?, direccion = ?, localidad = ?, provincia = ?, correo_electronico = ?, estado = ? WHERE id_cliente = ?;";
    private static final String altaLogica = "UPDATE clientes SET estado = 'true' WHERE id_cliente = ?;";
    private static final String modificarTelefonos = "UPDATE telefonos SET telefono1 = ?, telefono2 =? WHERE id_cliente =?";
    private static final String insertTelefonos = "INSERT INTO `bd_banco`.`telefonos` (telefono1, telefono2, id_cliente) VALUES (?, ?, ?)";
    private static final String listarIdClientes = "SELECT id_cliente FROM bd_banco.clientes";


	
    
    
    
    
    
    public int ModificacionTelefonos(String telefonoModificado1, String telefonoModificado2, int id_cliente) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

        Connection conexion = null;
        PreparedStatement statement = null;
        int filas = 0;

        try {
            conexion = conexionDB.getConnection();
            statement = conexion.prepareStatement(modificarTelefonos);

            statement.setString(1, telefonoModificado1);
            statement.setString(2, telefonoModificado2);
            statement.setInt(3, id_cliente);

            filas = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return filas;
    }
    
    
    
    public int AgregarTelefonos(String telefono1, String telefono2, int id_cliente) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }

        Connection conexion = null;
        PreparedStatement statement = null;
        int filas = 0;

        try {
            conexion = conexionDB.getConnection();
            statement = conexion.prepareStatement(insertTelefonos);

            statement.setString(1, telefono1);
            statement.setString(2, telefono2);
            statement.setInt(3, id_cliente);

            filas = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conexion != null) {
                try {
                    conexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return filas;
    }
    
    
    
    
    
    
    
    public int eliminarCliente(int id_cliente_borrar){
		try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		Connection conexion = null;
		PreparedStatement statement;
		int filas = 0;
		
		try
		{
			conexion = conexionDB.getConnection();


			statement = conexion.prepareStatement(deleteCliente);
			statement.setInt(1, id_cliente_borrar);

			if(statement.executeUpdate() > 0)
			{
				filas = 1;
				System.out.println("El Cliente fue ELIMINADO correctamente...");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return filas;
	}

    @Override
	public int agregarCliente(Cliente clienteNuevo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Provincia provincia = new Provincia();
		ProvinciaDao pDao = new ProvinciaDao();
		provincia = pDao.getProvinciaConId(clienteNuevo.getProvincia().getId_provincia());
		
		
		Localidad localidad = new Localidad();
		LocalidadDao lDao = new LocalidadDao();
		localidad = lDao.getLocalidadConId(clienteNuevo.getLocalidad().getIdLocalidad());
		
		
		Connection conexion = null;
		PreparedStatement statement;
		int filas = 0;
		UsuarioDao usuarioDao = new UsuarioDao();
		ResultSet resultSet = null;
		
		try
		{
			//String nuevoURL = host + dbName;
			//conexion = DriverManager.getConnection(nuevoURL, user, pass);
			conexion = conexionDB.getConnection();
			
			//crea el tipo de fecha compatible y lo asigna. Luego lo manda en el statement
			java.util.Date fechaUtil = clienteNuevo.getFechaNacimiento();
			java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
			
			statement = conexion.prepareStatement(insertCliente, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, clienteNuevo.getDni());
			statement.setString(2, clienteNuevo.getCuil());
			statement.setString(3, clienteNuevo.getNombre());
			statement.setString(4, clienteNuevo.getApellido());
			statement.setString(5, clienteNuevo.getSexo().name());
			statement.setInt(6, clienteNuevo.getNacionalidad().getIdPais());
			statement.setDate(7, fechaSql);
			statement.setString(8, clienteNuevo.getDireccion());
			statement.setInt(9, localidad.getIdLocalidad());
			statement.setInt(10, provincia.getId_provincia());
			statement.setString(11, clienteNuevo.getCorreoElectronico());
			statement.setString(12, clienteNuevo.getEstado().name());

			
			if(statement.executeUpdate() > 0)
			{
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					int id_cliente = resultSet.getInt(1);
	                clienteNuevo.setId(id_cliente);
	                if (AgregarTelefonos(clienteNuevo.getTelefono1().getTelefono(), clienteNuevo.getTelefono2().getTelefono() , id_cliente) == 1) {
	                	System.out.println("El Telefonos del cliente agregados correctamente...");
	                }
				}
				
				Usuario usuarioNuevo = new Usuario();
                usuarioNuevo.setUsuario(clienteNuevo.getCorreoElectronico());
                usuarioNuevo.setContrasena(clienteNuevo.getDni());
                usuarioNuevo.setAcceso("Cliente");
                usuarioNuevo.setCliente(clienteNuevo);
                usuarioNuevo.setEstado("True");
                
                if (usuarioDao.agregarUsuario(usuarioNuevo) == 1) {
                    System.out.println("El USUARIO: " + clienteNuevo.getCorreoElectronico() + " fue Creado correctamente con la contraseña: " + clienteNuevo.getDni());
                }
				filas = 1;
				System.out.println("El Cliente fue Agregado a la base de datos correctamente...");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return filas;
	}


	public Cliente buscar_con_id(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		Cliente cliente = null;
		
		try 
		{
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(buscarConId);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			resultSet.next();
			cliente = getCliente(resultSet);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return cliente;
	}
	
    
	private Cliente getCliente(ResultSet resultSet) {
		
		Cliente cliente = null;
		Telefono tel1 = new Telefono();
		Telefono tel2 = new Telefono();
		
		ProvinciaDao pDao = new ProvinciaDao();
	    Pais pais = new Pais();
		
	    PaisDao paisDao = new PaisDao();	     
		
		LocalidadDao lDao = new LocalidadDao();
		
		
		try {
			cliente = new Cliente();
			cliente.setId(resultSet.getInt("id_cliente"));
			cliente.setDni(resultSet.getString("dni"));
			cliente.setCuil(resultSet.getString("cuil"));
			cliente.setNombre(resultSet.getString("nombre"));
			cliente.setApellido(resultSet.getString("apellido"));
			cliente.setSexo(resultSet.getString("sexo"));
			pais.setNombre(resultSet.getString("nacionalidad"));
			cliente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
			cliente.setDireccion(resultSet.getString("direccion"));
			cliente.setLocalidad( lDao.getLocalidadConId(resultSet.getInt("localidad")) );
			cliente.setProvincia(pDao.getProvinciaConId(resultSet.getInt("provincia")));
			cliente.setNacionalidad(paisDao.getPaisConId(resultSet.getInt("nacionalidad")));
			cliente.setCorreoElectronico(resultSet.getString("correo_electronico"));
			cliente.setEstado(resultSet.getString("estado"));	
			
			if (  resultSet.getString("telefono1") != null )      {    tel1.setTelefono(resultSet.getString("telefono1")); } else {  tel1.setTelefono("Sin datos");}
			if (  resultSet.getString("telefono2") != null )      {    tel2.setTelefono(resultSet.getString("telefono2")); } else {  tel2.setTelefono("Sin datos");}
			//tel2.setTelefono(resultSet.getString("telefono2"));
			
			/*
			tel1.setIdTelefono(resultSet.getInt("id_telefono"));
			tel2.setIdTelefono(resultSet.getInt("id_telefono"));
			*/
			
			
			
			
			cliente.setTelefono1(tel1);
			cliente.setTelefono2(tel2);
		   
		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cliente;
	}

	@Override
	public ArrayList<Cliente> Listar() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cliente> listadoClientes = new ArrayList<Cliente>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				
				
				listadoClientes.add(getCliente(resultSet));
			}
		}
		
		catch(SQLException e){
			e.printStackTrace();
		}
		
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		return listadoClientes;
	}
	
	@Override
	public ArrayList<Cliente> ListarConEstadoFalse() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cliente> listadoClientes = new ArrayList<Cliente>();
		ArrayList<Cliente> listadoClientesFalse = new ArrayList<Cliente>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
		
			while(resultSet.next()) {	
				listadoClientes.add(getCliente(resultSet));
	
			}
			for (int i = 0; i < listadoClientes.size(); i++) {
			    if (listadoClientes.get(i).getEstado() == Cliente.ESTADO.False) {
			        listadoClientesFalse.add(listadoClientes.get(i));
			    }
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return listadoClientesFalse;
	}
	
	
	
	public ArrayList<Cliente> ListarConEstadoTrue() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cliente> listadoClientes = new ArrayList<Cliente>();
		ArrayList<Cliente> listadoClientesTrue = new ArrayList<Cliente>();
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
		
			while(resultSet.next()) {	
				listadoClientes.add(getCliente(resultSet));
	
			}
			for (int i = 0; i < listadoClientes.size(); i++) {
			    if (listadoClientes.get(i).getEstado() == Cliente.ESTADO.True) {
			        listadoClientesTrue.add(listadoClientes.get(i));
			    }
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		finally {
			if(conexion != null)
			{
				try 
				{
					conexion.close();
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
		return listadoClientesTrue;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public int BajaLogicaCliente(int idClienteBaja) {
	
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return 0;
	    }
	
	    Connection conexion = null;
	    PreparedStatement pst = null;
	    int filas = 0;

	    try {
	        conexion = conexionDB.getConnection();
	        pst = conexion.prepareStatement(bajaLogica);
	        pst.setInt(1, idClienteBaja);
	
	        filas = pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    
	        if (pst != null) {
	            try {
	                pst.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	
	        if (conexion != null) {
	            try {
	                conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return filas;	
	}
	
	@Override
	public int ModificacionCliente(Cliente clienteModificar) {
		
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return 0;
	    }
	
	    ProvinciaDao pDao = new ProvinciaDao();
	    LocalidadDao lDao = new LocalidadDao();
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    int filas = 0;

	    try {
	    	conexion = conexionDB.getConnection();
	        statement = conexion.prepareStatement(updateCliente);
	        
	        //crea el tipo de fecha compatible y lo asigna. Luego lo manda en el statement
	        java.util.Date fechaUtil = clienteModificar.getFechaNacimiento();
	        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
	        
			statement.setString(1, clienteModificar.getDni());
			statement.setString(2, clienteModificar.getCuil());
			statement.setString(3, clienteModificar.getNombre());
			statement.setString(4, clienteModificar.getApellido());
			statement.setString(5, clienteModificar.getSexo().name());
			statement.setInt(6, clienteModificar.getNacionalidad().getIdPais());
			statement.setDate(7, fechaSql);
			statement.setString(8, clienteModificar.getDireccion());
			statement.setInt(9, clienteModificar.getLocalidad().getIdLocalidad());
			statement.setInt(10, clienteModificar.getProvincia().getId_provincia());
			statement.setString(11, clienteModificar.getCorreoElectronico());
			statement.setString(12, clienteModificar.getEstado().name());
			statement.setInt(13,clienteModificar.getId());

			
	        filas = statement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	    } finally {
	    
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	
	        if (conexion != null) {
	            try {
	                conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return filas;	
	}


	public int AltaLogicaCliente(int idClienteAlta) {
		
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return 0;
	    }
	
	    Connection conexion = null;
	    PreparedStatement pst = null;
	    int filas = 0;

	    try {
	        conexion = conexionDB.getConnection();
	        pst = conexion.prepareStatement(altaLogica);
	        pst.setInt(1, idClienteAlta);
	
	        filas = pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    
	        if (pst != null) {
	            try {
	                pst.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	
	        if (conexion != null) {
	            try {
	                conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return filas;	
	}

	
	public ArrayList<Integer> listarIdClientes() {
	    ArrayList<Integer> listaIdClientes = new ArrayList<>();
	    
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return listaIdClientes; 
	    }

	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;

	    try {
	        conexion = conexionDB.getConnection();
	        statement = conexion.prepareStatement(listarIdClientes); 
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            listaIdClientes.add(resultSet.getInt("id_cliente"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conexion != null) {
	            try {
	                conexion.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return listaIdClientes;
	}
	
}







