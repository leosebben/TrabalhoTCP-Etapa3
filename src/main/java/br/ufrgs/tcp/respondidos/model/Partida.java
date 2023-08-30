package br.ufrgs.tcp.respondidos.model;
import java.util.List;


public class Partida {
    private BancoDeRespostas bancoDeRespostas;
    private List<Jogador> jogadores;
    private int turno;
    private Timer timer;
    private Resposta respAtual;
    private boolean acabou;

    private static final int TURNO_NAO_INICIADO = -1;
    private static final int TEMPO_POR_RESPOSTA = 30;

    public Partida(List<Jogador> jogadores) {
        this.bancoDeRespostas = BancoDeRespostas.carregarDoArquivo();
        this.jogadores = jogadores;
        this.turno = TURNO_NAO_INICIADO;
        this.timer = null;
        this.respAtual = null;
        this.acabou = false;
    }

    public Partida(List<Jogador> jogadores, int turno, Timer timer, Resposta respAtual) {
        this.jogadores = jogadores;
        this.turno = turno;
        this.timer = timer;
        this.respAtual = respAtual;
    }

    public void inicia(){
        this.turno = 0;
    }

    public void encerra(){

    }

    public Jogador getJogadorAtual(){
        return jogadores.get(turno);
    }

    public void escolherPergunta(Categoria categoria) {
        Resposta resposta = bancoDeRespostas.novaDaCategoria(categoria);
        this.respAtual = resposta;
        this.timer = new Timer( TEMPO_POR_RESPOSTA );
        this.timer.iniciar();
    }

	public Resposta getRespostaAtual() {
		return respAtual;
	}

    public Timer getTimer(){
        return timer;
    }

    public boolean acabou(){
        return this.acabou;
    }

    public void proximoTurno() {
        if( getJogadorAtual().jaGanhou() ){
            this.acabou = true;
        } else {
            turno = (turno + 1) % jogadores.size();
        }
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }
}
