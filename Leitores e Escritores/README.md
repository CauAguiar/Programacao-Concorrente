# Leitores e Escritores

Exemplo clássico do problema de leitores/escritores implementado em Java. O objetivo é demonstrar controle de acesso concorrente a um recurso compartilhado, permitindo múltiplos leitores simultâneos ou um único escritor.

<img src="leitorescritor.gif" alt="Leitores e Escritores preview" width="300" />

## Arquivos

- `Buffer.java` - Implementa o buffer compartilhado e mecanismos de sincronização.
- `Reader.java` - Exemplo de thread leitora.
- `Writer.java` - Exemplo de thread escritora.
- `Principal.java` - Classe com método `main` para executar a simulação.

## Como compilar e executar

No terminal, dentro da pasta `Leitores e Escritores` rode:

```bash
javac *.java
java Principal
```

## Imagens

Preview dos recursos gráficos usados pela aplicação (pasta `img/`):

<img src="img/steve.png" alt="preview" width="240" />

As demais imagens usadas pelo projeto estão na pasta `img/`.


