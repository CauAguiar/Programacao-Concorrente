# ProdutorConsumidor

Implementação do problema produtor/consumidor (buffer limitado) em Java. Este exemplo demonstra comunicação entre threads e sincronização para evitar condições de corrida e sobre/underflow do buffer.

<img src="produtorconsumidor.gif" alt="Produtor Consumidor preview" width="300" />

## Arquivos

- `Buffer.java` - Buffer compartilhado com operações de inserção/remoção sincronizadas.
- `Produtor.java` - Thread produtora que insere itens no buffer.
- `Consumidor.java` - Thread consumidora que remove itens do buffer.
- `Principal.java` - Ponto de entrada para executar a simulação.

## Como compilar e executar

```bash
javac *.java
java Principal
```

## Imagens

Preview (pasta `img/`):

<img src="img/hamburguer.png" alt="preview" width="240" />

As imagens da pasta `img/` representam itens simulados (hambúrguer, batata, etc.).

