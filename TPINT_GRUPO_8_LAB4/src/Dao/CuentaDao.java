package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Dominio.Cliente;
import Dominio.Cuenta;
import Dominio.Movimiento;
import Dominio.TiposCuenta;
import Dominio.Usuario;

public class CuentaDao implements iCuentaDao{

	private static final String selectAll = "SELECT * FROM cuentas";
	private static final String buscarConId = "SELECT * FROM `bd_banco`.`cuentas` WHERE `cuentas`.`id_cuenta` = ?";
	private static final String insertCuenta =  "INSERT INTO `bd_banco`.`cuentas`(id_cliente, fecha, id_tipo_cuenta, numero_cuenta, CBU, saldo, estado) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String bajaLogica = "UPDATE cuentas SET estado = 'false' WHERE id_cuenta = ?;";
	private static final String altaLogica = "UPDATE cuentas SET estado = 'true' WHERE id_cuenta = ?;";
	private static final String updateCuenta = "UPDATE cuentas SET saldo = ?, id_tipo_cuenta = ?  WHERE id_cuenta = ?;";
	private static final String cuentasPorCliente = "SELECT * FROM `bd_banco`.`cuentas` WHERE id_cliente = ? and estado = 'True';";    
	
	private static final String cantidadCuentas = "SELECT COUNT(*) FROM `bd_banco`.`cuentas`;";
	private static final String ultimoNumCuentaIngresado = "SELECT numero_cuenta FROM bd_banco.cuentas ORDER BY id_cuenta DESC LIMIT 1";
	private static final String traerUltimoCbu = "SELECT CBU FROM bd_banco.cuentas ORDER BY id_cuenta DESC LIMIT 1";
	
	private static final String cantidadCuentasPorCliente = "SELECT COUNT(*) FROM `bd_banco`.`cuentas` where id_cliente = ? AND estado = 'True'";
	
	
	//se modifica el saldo 
	private static final String modificarSaldo = "UPDATE cuentas SET saldo = ? WHERE id_cuenta = ?;";

	
	public int modificarMontoACuenta(float montoNuevo,  int id_cuenta) {
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
		        pst = conexion.prepareStatement(modificarSaldo);
		        pst.setFloat(1, montoNuevo);
		        pst.setInt(2, id_cuenta);
		
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
	
	
	public List<Cuenta> getListaCuentasPorCliente(int id_cliente) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cuenta> listadoCuentas = new ArrayList<Cuenta>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(cuentasPorCliente);
			statement.setInt(1, id_cliente);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listadoCuentas.add(getCuenta(resultSet));
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
		return listadoCuentas;
	}


	public ArrayList<Cuenta> Listar() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cuenta> listadoCuentas = new ArrayList<Cuenta>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				
				
				listadoCuentas.add(getCuenta(resultSet));
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
		
		return listadoCuentas;
	}

	
	private Cuenta getCuenta(ResultSet resultSet) {
		
		Cuenta cuenta = null;
		
		try {
			ClienteDao clDao = new ClienteDao();
			TiposCuentaDao tcDao = new TiposCuentaDao();
			cuenta = new Cuenta();
			
	        cuenta.setIdCuenta(resultSet.getInt("id_cuenta"));
	        cuenta.setCliente(clDao.buscar_con_id(resultSet.getInt("id_cliente")));
	        cuenta.setFecha(resultSet.getDate("fecha"));
	        cuenta.setTipoDeCuenta(tcDao.getTipoCuenta(resultSet.getInt("id_tipo_cuenta")));
	        cuenta.setCbu(resultSet.getString("cbu"));
	        cuenta.setSaldo(resultSet.getFloat("saldo"));
	        cuenta.setEstado(resultSet.getString("estado"));
		    cuenta.setNumeroCuenta(resultSet.getInt("numero_cuenta"));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return cuenta;
	}
	
	
	public Cuenta buscar_con_id(int id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		Cuenta cuenta = null;
		
		try 
		{
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(buscarConId);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			resultSet.next();
			cuenta = getCuenta(resultSet);
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
		return cuenta;
	}
	

	public ArrayList<Cuenta> ListarConEstadoFalse() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cuenta> listadoCuentas = new ArrayList<Cuenta>();
		ArrayList<Cuenta> listadoCuentasFalse = new ArrayList<Cuenta>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
		
			while(resultSet.next()) {	
				listadoCuentas.add(getCuenta(resultSet));
	
			}

			for (int i = 0; i < listadoCuentas.size(); i++) {
			    if (listadoCuentas.get(i).getEstado() == Cuenta.ESTADO.False) {
			    	listadoCuentasFalse.add(listadoCuentas.get(i));
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
		return listadoCuentasFalse;
	}

	
	public ArrayList<Cuenta> ListarConEstadoTrue() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection conexion = null;
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Cuenta> listadoCuentas = new ArrayList<Cuenta>();
		ArrayList<Cuenta> listadoCuentasTrue = new ArrayList<Cuenta>();
		
		try {
			conexion = conexionDB.getConnection();
			statement = conexion.prepareStatement(selectAll);
			resultSet = statement.executeQuery();
		
			while(resultSet.next()) {	
				listadoCuentas.add(getCuenta(resultSet));
	
			}

			for (int i = 0; i < listadoCuentas.size(); i++) {
			    if (listadoCuentas.get(i).getEstado() == Cuenta.ESTADO.True) {
			    	listadoCuentasTrue.add(listadoCuentas.get(i));
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
		return listadoCuentasTrue;
	}
	
	
	
	public int agregarCuenta(Cuenta cuentaNueva) {
		Cliente cliente = new Cliente();
		ClienteDao clDao = new ClienteDao();
		cliente = clDao.buscar_con_id(cuentaNueva.getCliente().getId());
		
		
		int filas = 0;
		int maximoCuentasPorCliente = 3;
		int cuentasActuales = cuentasDelCliente(cuentaNueva.getCliente().getId());
		float saldo_inicial = 10000;
	    
		if(cuentasActuales < maximoCuentasPorCliente && cliente != null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			Connection conexion = null;
			PreparedStatement statement;
			ResultSet resultSet = null;
			int id_cuenta = 0;
			
			try
			{
				conexion = conexionDB.getConnection();
				
				java.util.Date utilDate = new java.util.Date();
			    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				
				statement = conexion.prepareStatement(insertCuenta, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, cuentaNueva.getCliente().getId());
				statement.setDate(2, sqlDate);
				statement.setInt(3, cuentaNueva.getTipoCuenta().getIdTipoCuenta());
				statement.setLong(4,  cuentaNueva.getNumeroCuenta());
				
				statement.setString(5, cuentaNueva.getCbu());
				statement.setFloat(6, saldo_inicial);
				statement.setString(7, cuentaNueva.getEstado().name());
				if(statement.executeUpdate() > 0)
				{
					filas = 1;
					System.out.println("La cuenta se creo en la base de datos... generando Monto de ingreso...");
					resultSet = statement.getGeneratedKeys();
					if (resultSet.next()) {
						id_cuenta = resultSet.getInt(1);
					}
					MovimientoDao movDao = new MovimientoDao();
					String concepto = "SALDO INICIAL";
					int id_tipo_mov = 1;
					int origen = 1;
					if(movDao.agregarIngresoInicialAMovimiento(sqlDate, concepto, saldo_inicial, id_tipo_mov, origen, id_cuenta)>0) {
						System.out.println("Activar cuenta, ya se encuentra disponible...");
					}
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
		}
		return filas;
	}
	
	
	public int BajaLogicaCuenta(int idCuentaBaja) {
		
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
	        pst.setInt(1, idCuentaBaja);
	
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
	
	
	public int AltaLogicaCuenta(int idCuentaAlta) {
		
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
	        pst.setInt(1, idCuentaAlta);
	
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
	    
	public long ModificarCuenta(Cuenta cuentaModificar) {
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
	        e.printStackTrace();
	        return 0;
		}
		
	    Connection conexion = null;
	    PreparedStatement statement = null;
	    int filas = 0;
	
	    try {
	    	conexion = conexionDB.getConnection();
	        statement = conexion.prepareStatement(updateCuenta);
			statement.setFloat(1, cuentaModificar.getSaldo());
            statement.setInt(2, cuentaModificar.getTipoCuenta().getIdTipoCuenta()); 
     	    statement.setInt(3, cuentaModificar.getIdCuenta());
     	    
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
	
	      
	public long generarCbu() {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return 0;
	    }

	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    long ultimoCbu = 0;

	    try {
	        conexion = conexionDB.getConnection();
	        statement = conexion.prepareStatement(traerUltimoCbu);
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            ultimoCbu = resultSet.getLong("CBU");
	            ultimoCbu++;
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
	    return ultimoCbu;
	}

    
    
	public int cantidadRegistros() {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return 0;
	    }

	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    int cantidadDeCuentas = 0;

	    try {
	        conexion = conexionDB.getConnection();
	        statement = conexion.prepareStatement(cantidadCuentas);
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            cantidadDeCuentas = resultSet.getInt(1);
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
	    return cantidadDeCuentas;
	}

    
	public long generarNumeroCuenta() {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return 0;
	    }

	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    long ultimoNumCuenta = 0;

	    try {
	        conexion = conexionDB.getConnection();
	        statement = conexion.prepareStatement(ultimoNumCuentaIngresado);
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            ultimoNumCuenta = resultSet.getLong("numero_cuenta");
	            ultimoNumCuenta++;
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

	    return ultimoNumCuenta;
	}
	
	
	public void realizarTransferencia(String cbuOrigen, String cbuDestino, float monto) throws SQLException {
	    Connection conexion = null;
	    java.sql.CallableStatement cstmt = null;

	    try {
	        conexion = conexionDB.getConnection();
	        cstmt = conexion.prepareCall("{CALL TransferirDinero(?, ?, ?, ?, ?)}"); //se agregan dos output del SP en SQL (por que no se pasan parametros de id de cuenta con el cbu.... parche)
	        cstmt.setString(1, cbuOrigen);
	        cstmt.setString(2, cbuDestino);
	        cstmt.setDouble(3, monto);
	        cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
	        cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
	        
	        cstmt.executeUpdate();
	        
	        int idCuentaOrigen = cstmt.getInt(4);
	        int idCuentaDestino = cstmt.getInt(5);
	        
	        if(idCuentaOrigen > 1) {
	        	MovimientoDao movDao = new MovimientoDao();
	        	//debito primero el movimiento
	        	float montoNegativo = monto*-1;
	        	if(movDao.agregarTransferenciaAMovimiento("TRANSFERENCIA (EGRESO)", montoNegativo, idCuentaDestino, idCuentaOrigen)>0) {
	        		System.out.println("SE DESCONTO EL DINERO EN LA CUENTA " + idCuentaOrigen);
	        	}

	        	//ingreso el movimiento a favor
	        	if(movDao.agregarTransferenciaAMovimiento("INGRESO DE TRANSFERENCIA", monto, idCuentaOrigen, idCuentaDestino)>0) {
	        		System.out.println("SE ADICIONÓ EL DINERO EN LA CUENTA " + idCuentaDestino);
	        	}
	        }
	        
	        
	        
	    } finally {
	        if (cstmt != null) cstmt.close();
	        if (conexion != null) conexion.close();
	    }
	}
	
	
	public int cuentasDelCliente(int idCuenta) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return 0;
	    }

	    Connection conexion = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    int cantidadDeCuentas = 0;

	    try {
	        conexion = conexionDB.getConnection();
	        statement = conexion.prepareStatement(cantidadCuentasPorCliente);
	        statement.setInt(1, idCuenta);
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            cantidadDeCuentas = resultSet.getInt(1);
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
	    return cantidadDeCuentas;
	}
 
		    
		    
		    
	
	 
	
	
	

	
	
	
	
}
