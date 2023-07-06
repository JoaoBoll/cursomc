package com.example.cursomc;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Estado;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.repositories.CidadeRepository;
import com.example.cursomc.repositories.EstadoRepository;
import com.example.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run (String... args) throws Exception {

        Categoria cat1 = new Categoria(1, "Informatica");
        Categoria cat2 = new Categoria(2, "Escritório");

        Produto p1 = new Produto(null, "Computador", 2000.00);
        Produto p2 = new Produto(null, "Impressora", 800.00);
        Produto p3 = new Produto(null, "Mouse", 80.00);

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        Estado est1 = new Estado(null, "Minas gerais");
        Estado est2 = new Estado(null, "São paulo");

        Cidade c1 = new Cidade(null, "Uberlãndia", est1);
        Cidade c2 = new Cidade(null, "São paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas ", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

    }
}
