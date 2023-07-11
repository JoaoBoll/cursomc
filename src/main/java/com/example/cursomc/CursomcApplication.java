package com.example.cursomc;

import com.example.cursomc.domain.*;
import com.example.cursomc.enums.EstadoPagamento;
import com.example.cursomc.enums.TipoCliente;
import com.example.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run (String... args) throws Exception {

        Categoria cat1 = new Categoria(1, "Informatica");
        Categoria cat2 = new Categoria(2, "Escritório");
        Categoria cat3 = new Categoria(2, "Cama, mesa e banho");
        Categoria cat4 = new Categoria(2, "Eletrônicos");
        Categoria cat5 = new Categoria(2, "Jardinagem");
        Categoria cat6 = new Categoria(2, "Decoração");
        Categoria cat7 = new Categoria(2, "Perfumaria");


        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Minas gerais");
        Estado est2 = new Estado(null, "São paulo");


        Cidade c1 = new Cidade(null, "Uberlãndia", est1);
        Cidade c2 = new Cidade(null, "São paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas ", est2);


        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "34343434344", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("232323232", "232323232"));

        Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim","38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clienteRepository.saveAll(Arrays.asList(cli1));

        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");


        Pedido pd1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido pd2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pd1, 6);
        pd1.setPagamento(pag1);

        Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pd2, sdf.parse("20/10/2017 00:00"), null);
        pd2.setPagamento(pag2);

        cli1.getPedidos().addAll(Arrays.asList(pd1, pd2));

        pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
        pedidoRepository.saveAll(Arrays.asList(pd1, pd2));

        ItemPedido ip1 = new ItemPedido(pd1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(pd1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(pd2, p2, 100.00, 1, 800.00);

        p1.getItens().addAll(Arrays.asList(ip1, ip2));
        p2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

    }
}
