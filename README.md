# Programação Concorrente — Trabalhos

Este repositório reúne trabalhos da disciplina de Programação Concorrente, implementados em Java. Cada pasta contém um pequeno projeto ou simulação relacionada a threads, sincronização e comunicação entre processos.

## Índice de Projetos

- **Leitores e Escritores** — Exemplo clássico de sincronização entre leitores e escritores. Veja `Leitores e Escritores/README.md`.
  - Preview:
  
  <img src="Leitores e Escritores/leitorescritor.gif" alt="Leitores e Escritores preview" width="500" />

- **ProdutorConsumidor** — Implementação do problema produtor/consumidor com buffer compartilhado. Veja `ProdutorConsumidor/README.md`.
  - Preview:

  <img src="ProdutorConsumidor/produtorconsumidor.gif" alt="Produtor Consumidor preview" width="500" />

- **Simulação de Trânsito** — Projeto que usa uma representação inspirada no Pac-Man para simular tráfego e semáforos. Veja `SimulaçãodeTrânsito/README.md`.
  - Preview:

  <img src="SimulaçãodeTrânsito/semaforopacman.gif" alt="Simulação Trânsito preview" width="500" />

- **SimuladorDeTrem** — Simulador de trem com interface JavaFX completo com threads. Veja `SimuladorDeTrem/README.md`.
  - Preview:

  <img src="SimuladorDeTrem/simuladordetremthreads.gif" alt="Simulador de Trem preview" width="500" />

- **Trens sem Threads** — Versão simples do simulador de trens. Veja `Trens sem Threads/README.md`.
  - Preview:

  <img src="Trens sem Threads/simuladordetrem.gif" alt="Trens sem Threads preview" width="500" />

## Como Compilar e Executar

Os projetos são em Java (simples, sem build system). Para compilar e executar cada projeto, entre na pasta do projeto e use:

```bash
javac *.java
java Principal
```

ou, quando a classe principal tiver outro nome, use o nome indicado no `README.md` da pasta.

## Contribuições

Estes projetos foram desenvolvidos como trabalhos acadêmicos; sinta-se à vontade para explorar e abrir issues se encontrar bugs ou quiser sugerir melhorias.
