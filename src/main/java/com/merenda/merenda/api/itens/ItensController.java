package com.merenda.merenda.api.itens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/itens")
public class ItensController {
    @Autowired
    private ItensService service;


    @GetMapping()
    public ResponseEntity get() {
        List<ItensDTO> carros = service.getCarros();
        return ResponseEntity.ok(carros);
    }
    @GetMapping("/afi")
    public ResponseEntity get2() {
        List<ItensDTO> carros = service.getCarros2();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/pedidos")
    public ResponseEntity get3() {
        List<ItensDTO> carros = service.getCarros3();
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        ItensDTO carro = service.getCarroById(id);

        return ResponseEntity.ok(carro);
    }
    @GetMapping("/pedido/{pedido}")
    public ResponseEntity getCarrosByPedido(@PathVariable("pedido") String pedido) {
        List<ItensDTO> carros = service.getCarrosByPedido(pedido);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }


    @GetMapping("/pedidoall/{pedido}")
    public ResponseEntity getCarrosByPedidoAll(@PathVariable("pedido") String pedido) {
        List<ItensDTO> carros = service.getCarrosByPedidoAll(pedido);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }


    @GetMapping("/af/{af}")
    public ResponseEntity getCarrosByAf(@PathVariable("af") Long af) {
        List<ItensDTO> carros = service.getCarrosByAf(af);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/escola/{escola}/{pedido}")
    public ResponseEntity getCarrosByEscola(@PathVariable("escola") Long escola,@PathVariable("pedido") Long pedido) {
        List<ItensDTO> carros = service.getCarrosByEscola(escola,pedido);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/escolar/{escola}/{ano}")
    public ResponseEntity getEscolar(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getEscolar(escola,ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/fornecedor/{fornecedor}")
    public ResponseEntity getByFornecedor(@PathVariable("fornecedor") Long fornecedor) {
        List<ItensDTO> carros = service.getByFornecedor(fornecedor);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/escolaAll/{ano}")
    public ResponseEntity getEscolaAll(@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getEscolaAll(ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/ano/{ano}")
    public ResponseEntity getItensAno(@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getItensAno(ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/maispedidos/{ano}")
    public ResponseEntity getMaisPedidos(@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getMaisPedidos(ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }
     //verificado
    @GetMapping("/totalMes/{ano}")
    public ResponseEntity getTotalMes(@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getTotalMes(ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }
    //verificado
    @GetMapping("/totalCategoria/{ano}")
    public ResponseEntity getTotalCategoria(@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getTotalCategoria(ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }



    @GetMapping("/totalMesNivel/{nivel}/{ano}")
    public ResponseEntity getTotalMesNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getTotalMesNivel(nivel,ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/totalMesEscola/{escola}/{ano}")
    public ResponseEntity getTotalMesEscola(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getTotalMesEscola(escola,ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }





    @GetMapping("/totalCategoriaNivel/{nivel}/{ano}")
    public ResponseEntity getTotalCategoriaNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getTotalCategoriaNivel(nivel,ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/totalCategoriaEscola/{escola}/{ano}")
    public ResponseEntity getTotalCategoriaEscola(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getTotalCategoriaEscola(escola,ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/totalEscolas/{ano}")
    public ResponseEntity getTotalEScolas(@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getTotalEscolas(ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/totalEscolaNivel/{nivel}/{ano}")
    public ResponseEntity getTotalEScolaNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getTotalEscolaNivel(nivel,ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    //verificado
    @GetMapping("/mediaAlunos/{ano}")
    public ResponseEntity getMediaAlunos(@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getMediaAlunos(ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/mediaAlunosNivel/{nivel}/{ano}")
    public ResponseEntity getMediaAlunosNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getMediaAlunosNivel(nivel,ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @GetMapping("/produtos/{produto}/{ano}")
    public ResponseEntity getProdutos(@PathVariable("produto") Long produto,@PathVariable("ano") Long ano) {
        List<ItensDTO> carros = service.getProdutos(produto,ano);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    //verificado
    @GetMapping("/total/{ano}")
    public double getTotal(@PathVariable("ano") Long ano) {
        return service.getTotal(ano);
    }

//verificado
    @GetMapping("/tradicional/{ano}")
    public double getTradicional(@PathVariable("ano") Long ano) {
        return service.getTradicional(ano);
    }

    //verificado
    @GetMapping("/familiar/{ano}")
    public double getFamiliar(@PathVariable("ano") Long ano) {
        return service.getFamiliar(ano);
    }


    @GetMapping("/totalNivel/{nivel}/{ano}")
    public double getTotalNivel(@PathVariable("nivel") Long nivel, @PathVariable("ano") Long ano) {
        return service.getTotalNivel(nivel,ano);
    }

//verificado
    @GetMapping("/totalEscola/{escola}/{ano}")
    public double getTotalEscola(@PathVariable("escola") Long escola, @PathVariable("ano") Long ano) {
        return service.getTotalEscola(escola,ano);
    }


    @GetMapping("/somaAll/{escola}")
    public double getRep(@PathVariable("escola") Long escola) {
        return service.getSoma(escola);
    }

    @GetMapping("/totalAgroEscola/{escola}/{ano}")
    public double getTotalAgroEscola(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        return service.getTotalAgroEscola(escola,ano);
    }

    @GetMapping("/totalAgroNivel/{escola}/{ano}")
    public double getTotalAgroNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        return service.getTotalAgroNivel(nivel,ano);
    }

    @GetMapping("/totalAgro/{ano}")
    public double getTotalAgro(@PathVariable("ano") Long ano) {
        return service.getTotalAgro(ano);
    }

    @GetMapping("/totalPedido/{pedido}")
    public double getTotalPedido(@PathVariable("pedido") String pedido) {
        return service.getTotalPedido(pedido);
    }

    @GetMapping("/totalAf/{af}")
    public double getTotalAf(@PathVariable("af") Long af) {
        return service.getTotalAf(af);
    }



    @GetMapping("/tradicionalNivel/{nivel}/{ano}")
    public double getTradicionalNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        return service.getTradicionalNivel(nivel,ano);
    }
    //verificado
    @GetMapping("/tradicionalEscola/{escola}/{ano}")
    public double getTradicionalEscola(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        return service.getTradicionalEscola(escola,ano);
    }


    @GetMapping("/diversos/{ano}")
    public double getDiversos(@PathVariable("ano") Long ano) {
        return service.getDiversos(ano);
    }


    @GetMapping("/diversosNivel/{nivel}/{ano}")
    public double getDiversosNivel(@PathVariable("nivel") Long nivel,@PathVariable("ano") Long ano) {
        return service.getDiversosNivel(nivel,ano);
    }


    @GetMapping("/diversosEscola/{escola}/{ano}")
    public double getDiversosEscola(@PathVariable("escola") Long escola,@PathVariable("ano") Long ano) {
        return service.getDiversosEscola(escola,ano);
    }

    @GetMapping("/cart/{escola}")
    public double getCart(@PathVariable("escola") Long escola) {
        return service.getCart(escola);
    }

    @GetMapping("/estoque/{id}")
    public double getEstoque(@PathVariable("id") Long id) {
        return service.getEstoque(id);
    }


    @PostMapping
    public ResponseEntity post(@RequestBody Itens pedidoItens) {

        ItensDTO c = service.insert(pedidoItens);

        URI location = getUri(c.getId());
        return ResponseEntity.created(location).body(c);
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Itens pedidoItens) {
        pedidoItens.setId(id);
        ItensDTO c = service.update(pedidoItens, id);
        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);

        return ResponseEntity.ok().build();
    }
}
