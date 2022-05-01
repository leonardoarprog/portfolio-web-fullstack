import { Component, OnInit, ViewChild } from '@angular/core';
import {
  ApexNonAxisChartSeries,
  ApexPlotOptions,
  ApexChart,
  ApexLegend,
  ApexResponsive,
  ChartComponent
} from "ng-apexcharts";

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
  selector: 'app-skills',
  templateUrl: './skills.component.html',
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {


  @ViewChild("chart") chart: ChartComponent | any;
  public chartOptionsMulti: Partial<ChartOptions> | any;
  public chartOptionsSimple: Partial<ChartOptions> | any;


  constructor() { }

  public chartsData = [
    {
      "id": 1,
      "skill": "tecnologías",
      "tipo_grafico": "simple",
      "datos": {
        "HTML": 34,
        "CSS3": 12,
        "Javascript": 78,
        "Typescript": 90,
        "Java": 54,
        "Springboot": 71
      }
    }, {
      "id": 3,
      "skill": "idiomas",
      "tipo_grafico": "multi",
      "datos": {
        "Inglés": 34,
        "Español": 12,
        "Portugues": 78,
        "Frances": 90
      }
    }
  ]

  ngOnInit(): void {

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
  }

}