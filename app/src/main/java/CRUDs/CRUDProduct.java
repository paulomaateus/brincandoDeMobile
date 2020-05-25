package CRUDs;

import java.util.HashMap;
import java.util.Map;
import objects.products;
import objects.productsPack;

public class CRUDProduct {
    /**
     * Nesse mapa esta salvo qualquer produto que exista na loja, de uma bala a 500 unidades de cacha�a.
     */
    private Map<String, products> products;


    /**
     * Nesse mapa esta salvo apenas produtos que sejam pacotes. Esses pacotes tem que ter a quantidade de
     * produtos que contem.
     */
    private Map<String, productsPack> packs;

    /**
     * No construtor do CRUD e feito apenas a inicializacao das estruturas de dados.
     */
    public CRUDProduct() {
        this.products = new HashMap<>();
        this.packs = new HashMap<>();
    }

    /*pode haver um erro de funcionalidade aqui. Quando a unidade ja estiver cadastrada, o preco de compra da unidade pode ser diferente ao
     * do pacote. Verificar depois qual melhor solucao. (RESOLVIDO)
     */
    /**
     * Nesse metodo serao cadastrados pacotes de produtos ao sistema. Se informa o nome, preco que foi comprado
     * o produto e quantas unidades aquele produto tem.
     * Quando o objeto pacote e criado ele e salvo dentro do map da pacotes. A chave e o nome daquele produto.
     * Quando um pacote e adicionado, a sua unidade tambem e adicionada ao map de produtos, e a chave no map tambem e
     * o nome daquele produto.
     *
     * @param name Nome do produto que esta se adicionado.
     * @param buyPrice Preco que o produto foi comprado.
     * @param unitsInPack Quantidade de unidades que o pacote devera ter.
     */
    public void insertPack (String name, double  buyPrice, int unitsInPack ) {

        if(this.products.containsKey(name)) {
            throw new IllegalArgumentException("Erro! o produto que se tenta cadastrar ja esta cadastrado no sistema!");
        }if(!this.products.containsKey(name)) {
            this.insertUnity(name, buyPrice/unitsInPack);
        }else{
            this.products.get(name).setBuyPrice(buyPrice/unitsInPack);
        }
        this.products.put(name, new productsPack(name, buyPrice, unitsInPack));
    }

    /**
     * Metodo que funciona para o cadastro de unidades e funciona da mesma forma que o metodo que cadastra pacotes.
     * Porem, esse metodo sera usado apenas quando o usuario quiser cadastrar produtos que ele nao comprara pacotes mas
     * apenas unidades.
     *
     * @param name Nome do produto a ser adicionado.
     * @param buyPrice Preco que o produto foi comprado.
     */
    public void insertUnity(String name, double buyPrice) {
        if(this.products.containsKey(name)) {
            throw new IllegalArgumentException("Erro! o produto que se tenta cadastrar ja esta cadastrado no sistema!");
        }
        this.products.put(name, new products(name, buyPrice));
    }

    /**
     * Depois de pre-cadastrado, pode-se adicionar pacotes do respectivo produto. Apenas precisa ser informado o nome e
     * a quantidade de pacotes que se quer adicionar.
     * Quando o pacote e adicionado, as unidades que contem naquele pacote tambem serao contabilizadas no sistema.
     * @param name Nome do produto que se quer adicionar.
     * @param amountPacks Quantidade de pacotes do respectivo produto.
     */
    public void addPacks(String name, int amountPacks) {
        if(!this.packs.containsKey(name)) {
            throw new IllegalArgumentException("Erro, produto nao esta cadastrado no sistema");
        }
        this.packs.get(name).updateAmount(amountPacks);
        this.products.get(name).updateAmount((this.packs.get(name).getAmountUnits()) * amountPacks);
    }

    /**
     * Adiciona unidades de produtos no sistema. Ao adicionar unidades que formem um pacote, um pacote e adicionado ao mapa
     * de pacotes mas as unidades permanecem salvas. ao vender unidades que desmanchem um pacote, os pacotes desmanchados sao excluidos
     * do mapa de pacotes.
     * @param name
     * @param amountUnits
     */
    public void addUnits(String name, int amountUnits) {
        if(!this.products.containsKey(name)){
            throw new IllegalArgumentException("Erro, produto nao esta cadastrado no sistema!");
        }
        this.products.get(name).updateAmount(amountUnits);
        if(this.packs.containsKey(name))accountProducts(name);
    }
    /**
     * Metodo privado para auxiliar na atualizacao das estruturas de dados e atualizacao da quantidade de cada protudo.
     * Esse metodo atualiza quantidade de pacotes ou unidades quando cada um � adicionado, vendido ou zerado do sistema.
     * @param name Nome do produto a ser contabilizado.
     */
    private void accountProducts(String name) {
        int numberPacksPerUnity = this.products.get(name).getAmount() / this.packs.get(name).getAmountUnits();
        int amountPacks = this.packs.get(name).getAmount();
        this.packs.get(name).updateAmount(numberPacksPerUnity - amountPacks);
    }

    /**
     * Metodo que seta o preco de venda de um pacote. O preco nao pode ser menor ou igual ao preco que o produto foi comprado
     * @param name nome do produto
     * @param price preco de venda do produto
     */
    public void setPackSellPrice(String name, double price) {
        if(!this.packs.containsKey(name)) {
            throw new IllegalArgumentException("Produto nao existe.");
        }
        if(this.packs.get(name).getBuyPrice() >= price) {
            throw new IllegalArgumentException("O preco para se vender o produto nao pode ser menor ou igual que o preco que ele foi comprado.");
        }
        this.packs.get(name).setSellPrice(price);
    }

    /**
     * Metodo que seta o preco de venda de uma unidade. O preco nao pode ser menor ou igual ao preco que a unidade foi comprada.
     * @param name nome do produto
     * @param price preco de venda do produto.
     */
    public void setUnitySellPrice(String name, double price){
        if(!this.products.containsKey(name))throw new IllegalArgumentException("Produto inexistente.");
        if(this.products.get(name).getBuyPrice() >= price)throw new IllegalArgumentException("O preco para se vender o produto nao pode ser menor ou igaul ao preco ue foi comprado.");
        this.products.get(name).setSellPrice(price);
    }

    // metodos auxiliares para testes.
    public String toStringPacotes(){
        String retorno = "";
        for(productsPack produto : this.packs.values()){
            retorno += products.toString() + "\n";
        }
        return retorno;
    }
    public String toStringProdutos(){
        String retorno = "";
        for(products produto : this.products.values()){
            retorno += produto.toString() + "\n";
        }
        return retorno;
    }
    public String getPrecoVendaPacote(String nome){
        if(this.packs.containsKey(nome)){
            return "R$" +  this.packs.get(nome).getSellPrice();
        }else{
            return "produto inexistente";
        }
    }
    public String getPrecoVendaProduto(String nome){
        if(this.products.containsKey(nome)){
            return "R$" + this.products.get(nome).getSellPrice();
        }else{
            return "produto inexistente.";
        }
    }

    public int getUnidades (String nome){
        return this.products.get(nome).getAmount();
    }
    public int getPacotes (String nome){
        return this.packs.get(nome).getAmount();
    }
    //fim dos metodos auxiliares.

    /**
     * Metodo para vender produto que e um pacote. Esse metodo deve subtrair nos dois mapas a quantidade de pacotes vendidos
     * Esse metodo retorna um objeto produtoPacote.
     * @param name: pacote a ser vendido
     * @param price: preco que o pacote esta sendo vendido
     * @param amount: quantidade de pacotes vendidos
     * @return retorna um produtoPacote para ser usado em metodos de registros de vendas em outra classe
     */
    public productsPack sellPacks(String name, double price, int amount) {
        if(!this.packs.containsKey(name)) {
            throw new IllegalArgumentException("Produto nao existe.");
        }
        if(this.packs.get(name).getBuyPrice() >= price) {
            throw new IllegalArgumentException("Voce nao pode vender o produto pelo mesmo preco que comprou ou mais barato.");

        }
        products sellProduct =  this.packs.get(name).clone();
        sellProduct.setAmount(amount);
        sellProduct.setSellPrice(price);
        this.packs.get(name).updateAmount(-amount);
        this.products.get(name).updateAmount(-amount * this.packs.get(name).getAmountUnits());
        return (productsPack) sellProduct;

    }
    /**
     * Metodo para realizar a venda de um produto que e uma unidade nos sistema. Ao ser vendido as unidades devem ser subtraidas
     * dos dois mapas
     * @param name: Nome do produto a ser vendido
     * @param price: preco do produto a ser vendido
     * @param amount: quantidade do produto a ser vendido
     * @return retorna um produto unidade para que seja usado em metodos de registros de vendas
     */
    public products sellUnitys(String name, double price, int amount){
        if(!this.products.containsKey(name))throw new IllegalArgumentException("Produto nao existe");
        if(this.products.get(name).getBuyPrice() >= price) throw new IllegalArgumentException("Voce nao pode vender o produto pelo mesmo preco que comprou ou mais barato.");
        products sellProduct = this.products.get(name).clone();
        sellProduct.setAmount(amount);
        sellProduct.setSellPrice(price);
        this.products.get(name).updateAmount(-amount);
        this.accountProducts(name);
        return sellProduct;
    }
}
