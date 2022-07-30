import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  ApexNonAxisChartSeries,
  ApexPlotOptions,
  ApexChart,
  ApexLegend,
  ApexResponsive,
  ChartComponent
} from "ng-apexcharts";
import { ToastrService } from 'ngx-toastr';
import { HabilidadService } from 'src/app/servicios/habilidad.service';
import { TokenService } from 'src/app/servicios/token.service';


export type ChartOptions = {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  labels: string[];
  colors: string[];
  legend: ApexLegend;
  plotOptions: ApexPlotOptions;
  responsive: ApexResponsive | ApexResponsive[];
};

@Component({
  selector: 'app-persona-habilidad',
  templateUrl: './persona-habilidad.component.html',
  styleUrls: ['./persona-habilidad.component.css']
})
export class PersonaHabilidadComponent implements OnInit {

  @ViewChild("chart") chart: ChartComponent | any;
  public chartOptionsMulti: Partial<ChartOptions> | any;
  public chartOptionsSimple: Partial<ChartOptions> | any;

  chartsData!: any[];
  filtradosTecnologias: any[] = [];
  filtradosIdiomas: any[] = [];
  datosTecnologias!: any;
  datosIdiomas!: any;

  constructor(
    private tokenService: TokenService,
    private habilidadServicio: HabilidadService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService
  ) { }

  isLogged = false;
  errorMsj: any;

  ngOnInit(): void {

    const nombre_usuario = this.activatedRoute.snapshot.parent?.children[0].params['nombreUsuario'];

    if (this.tokenService.getToken() && this.tokenService.getUserName() === nombre_usuario) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }

    this.habilidadServicio.obtenerPersonaHabilidadPorUsuario(nombre_usuario).subscribe({
      next: (data: any) => {

        for (let i = 0; i < data.length; i++) {

          if (data[i].categoria_habilidad == "Idiomas") {
            this.filtradosIdiomas.push(data[i]);
          } else {
            this.filtradosTecnologias.push(data[i]);
          }
        }

        this.filtradosIdiomas.forEach(a => delete a.categoria_habilidad);
        this.datosIdiomas = Object.assign({}, this.filtradosIdiomas);
        this.filtradosTecnologias.forEach(a => delete a.categoria_habilidad);
        this.datosTecnologias = Object.assign({}, this.filtradosTecnologias);
        

        this.chartsData = [

          {
            "id": 1,
            "skill": "tecnologías",
            "tipo_grafico": "simple",
            "datos": this.datosTecnologias
          }, {
            "id": 2,
            "skill": "idiomas",
            "tipo_grafico": "multi",
            "datos": this.datosIdiomas
          }
        ]

        this.intializationChartoption();
      },

      error: _err => {
        this.router.navigate(['']);
      },
    });

  }

  intializationChartoption(): void {

    this.chartOptionsMulti = {
      series: [],
      chart: {
        width: '100%',
        type: 'radialBar',
        redrawOnParentResize: true,
        redrawOnWindowResize: true,
      },
      grid: {
        padding: {
          top: 0,
          right: 0,
          bottom: 0,
          left: 0
        },
      },
      plotOptions: {
        radialBar: {
          offsetY: 0,
          startAngle: 0,
          endAngle: 270,
          hollow: {
            margin: 5,
            size: '30%',
            background: 'transparent',
            image: undefined,
          },
          track: {
            background: '#e3e9ec',
          },
          dataLabels: {
            name: {
              show: false,
            },
            value: {
              show: false,
            }
          }
        }
      },

      colors: ['#4abbdb', '#7dbef1', '#5b6fa2', '#3288b0'],
      labels: [],
      legend: {
        show: true,
        floating: true,
        fontSize: '15px',
        position: 'left',
        offsetX: 60,
        offsetY: 15,
        labels: {
          useSeriesColors: true
        },
        markers: {
          size: 0
        },
        formatter: function (seriesName: string, opts: { w: { globals: { series: { [x: string]: string; }; }; }; seriesIndex: string | number; }) {
          return seriesName + ":  " + opts.w.globals.series[opts.seriesIndex];
        },
        itemMargin: {
          vertical: 3
        }
      },
      responsive: [{
        breakpoint: 992,
        options: {
          plotOptions: {
            radialBar: {
              offsetY: 60,
            }
          },
          legend: {
            show: true,
            height: '100%',
            fontSize: '14px',
            floating: true,
            position: 'center',
            horizontalAlign: 'left',
            offsetY: 0,
            offsetX: 0,
          },
        }
      }]
    };

    this.chartOptionsSimple = {

      colors: ['#4abbdb'],
      series: [],
      chart: {
        width: '100%',
        type: 'radialBar',
      },

      grid: {

        padding: {
          top: 0,
          right: 0,
          bottom: 0,
          left: 0
        },
      },

      plotOptions: {
        radialBar: {
          hollow: {
            size: '60%',
          },
          track: {
            background: '#e3e9ec'
          },
        },
      },
      labels: [],
    };
  }

  borrarPersHab(id: any) {

    this.habilidadServicio.borrarPersonaHabilidad(id).subscribe({
      next: () => {
        window.location.reload();
        this.toastr.success('Datos borrados correctamente', 'OK', {
          timeOut: 3000, positionClass: 'toast-top-center'
        });
      },
      error: err => {
        this.errorMsj = err.message;
        this.toastr.error(this.errorMsj, 'Error en el borrado', {
          timeOut: 3000, positionClass: 'toast-top-center',
        });
      },
    });
  }
}
