package cl.araucana.tupla2.multithread;

/**
 * Implementa el concepto de "semasforo" para las ejecuciones de Threads.
 * Es un contenedor de procesos Runnable que permite tener un maximo de procesos corriendo simultaneamente.
 * @author Administrador
 *
 */
public class Queue {
	private Thread[] listaThreads;
	private int bussy;
	private final int MAX_PROCESS = 3;

	public Queue() {
		listaThreads = new Thread[MAX_PROCESS];
		bussy = 0;
	}

	/**
	 * Inicia un proceso Runnable. En caso de existir MAX_PROCESS corriendo, espera hasta que
	 * algun proceso se detiene.
	 * @param p
	 */
	public synchronized void initProcess(Thread p) {
		if (bussy >= listaThreads.length) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		bussy++;
		//Busca un "espacio" para ubicar al proceso.
		boolean iniciado = false;
		//System.out.println(p.getName()+"Buscando espacio en cola");
		for (int i = 0; i < listaThreads.length && !iniciado; i++) {
			if (listaThreads[i] == null || !listaThreads[i].isAlive()) {
				System.out.println(p.getName() + " Ocupando:" + i);
				bussy++;
				listaThreads[i] = p;
				listaThreads[i].start();
				iniciado = true;
			}
		}
	}

	public boolean allFinished() {
		for (int i = 0; i < listaThreads.length; i++) {
			if (listaThreads[i] != null && listaThreads[i].isAlive())
				return false;
		}
		return true;
	}

	/**
	 * Permite informar al contenedor de procesos que un proceso ha finalizado.
	 */
	public synchronized void endProcess() {
		bussy--;
		notify();
	}
}
