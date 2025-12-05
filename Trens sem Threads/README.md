# Trens sem Threads

Versão simples (sem uso de threads) para simulação de trens. Este diretório contém uma implementação enxuta que demonstra técnicas de coordenação/exclusão mútua sem o uso de múltiplas threads no próprio simulador — as técnicas demonstradas e documentadas aqui são:

- Estrita alternância
- Solução de Peterson
- Variável de travamento (lock variable)

Esses métodos são apresentados como exercícios didáticos para comparar comportamentos e limitações.

## Arquivos

- `Principal.java` - Classe principal que inicializa a simulação.
- Imagens: vários arquivos `car*.png`, `bluetrain.png`, `redtrain.png` usados para representar os trens e a rodovia de carros.

## Como compilar e executar

```bash
javac Principal.java
java Principal
```

## Imagens

Preview:

<img src="car1.png" alt="car1" width="300" />

As imagens estão no diretório do projeto (não há pasta `img/` separada aqui).
