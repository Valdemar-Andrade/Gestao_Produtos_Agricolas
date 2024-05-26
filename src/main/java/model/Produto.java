/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author valdemar
 */
public class Produto {
    
    private Integer pkProduto;
    private String descricao;
    private Integer quantidade;
    private Double preco;

    public Produto () {
    }

    public Produto ( Integer pkProduto, String descricao, Integer quantidade, Double preco ) {
        this.pkProduto = pkProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Integer getPkProduto () {
        return pkProduto;
    }

    public void setPkProduto ( Integer pkProduto ) {
        this.pkProduto = pkProduto;
    }

    public String getDescricao () {
        return descricao;
    }

    public void setDescricao ( String descricao ) {
        this.descricao = descricao;
    }

    public Integer getQuantidade () {
        return quantidade;
    }

    public void setQuantidade ( Integer quantidade ) {
        this.quantidade = quantidade;
    }

    public Double getPreco () {
        return preco;
    }

    public void setPreco ( Double preco ) {
        this.preco = preco;
    }
    
    
    
}
