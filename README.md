# JARRV
<div align="left">
  <picture>
    <!-- Dark mode image -->
    <source srcset="imagens/LogoJARRVofc-dark.png" media="(prefers-color-scheme: dark)" />
    <!-- Light mode image -->
    <source srcset="imagens/LogoJARRVofc.png" media="(prefers-color-scheme: light)" />
    <!-- Default/fallback image -->
    <img src="imagens/LogoJARRVofc-dark.png" alt="Logo" width="800"/>
  </picture>
</div>

<br><br>

## Desenvolvimento Ágil - EC46C - 2025/2

<br>

 
## Integrantes
| Alunos |
|:--------|
| [Arthur Henrique Jardim](https://github.com/arthur-hj)  |
| [João Alberto Benaci](https://github.com/k7vinilstorage) |
| [Rafael Munhoz Castro](https://github.com/RafinhaW74)   |
| [Vinícius Souza Dias](https://github.com/vinicius-10) |

<br><br>
#
Sumario
|:--------|
| [Requisitos funcionais](https://github.com/k7vinilstorage/Tire-temperature-monitor/blob/main/Requisitos%20de%20usu%C3%A1rio/Requisitos%20Funcionais.md)  |
| [Requisitos não funcionais](https://github.com/k7vinilstorage/Tire-temperature-monitor/blob/main/Requisitos%20de%20usu%C3%A1rio/Requisitos%20Nao%20Funcionais.md)  |
| [Histórias de usuario](https://github.com/k7vinilstorage/Tire-temperature-monitor/blob/main/Requisitos%20de%20usu%C3%A1rio/Historias%20de%20Usuarios.md)  |
| [Histórias de usuario GPT](https://github.com/k7vinilstorage/Tire-temperature-monitor/blob/main/Requisitos%20de%20usu%C3%A1rio/Prompt-Historias-Usuarios.pdf) |
| [Protótipo de alta fidelidade](https://github.com/k7vinilstorage/Tire-temperature-monitor/blob/main/Prot%C3%B3tipo%20de%20alta%20fidelidade/Link%20para%20prot%C3%B3tipo.md)  |
| [Requisitos de Sistema](https://github.com/k7vinilstorage/Tire-temperature-monitor/blob/main/Requisitos%20de%20Sistema/Descri%C3%A7%C3%A3oPasta.md)  |
| [Atividade 9 Branch](https://github.com/k7vinilstorage/Tire-temperature-monitor/blob/main/branch.md) |

## Objetivo
Desenvolver um sistema de monitoramento de temperatura de pneus para protótipo do projeto de extensão Fórmula CP, utilizando sensores infravermelho e um microcontrolador e enviar as leituras dos sensores para o sistema de telemetria já existente do projeto. Projetando uma interface gráfica para visualização dos dados.

<br><br>

## Público-Alvo
O público-alvo do projeto são os membros do projeto de extensão do Fórmula CP e entusiastas.

<br><br>

## Sobre o sistema
### tire-temperature-monitor
O sistema consiste em 4 sensores de temperatura infravermelho (GY906) conectados a um microcontrolador ESP32-C3 Mini e um módulo can MCP2551. O sistema realizará a leitura dos sensores de temperatura e enviará os dados para o sistema de telemetria já existente. Além disso, será implementada uma interface gráfica em java para apresentar os dados recebidos.

