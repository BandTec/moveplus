package com.mycompany.moveplus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class Graficos extends JInternalFrame {

    private static final float MINMAX = 100;
    private static final int COUNT = 2 * 60;
    private Timer timer;
    String grafico = null;

    public Graficos(String tipoGrafico) {
        Date date = new Date();
        
        final DynamicTimeSeriesCollection dataset
                = new DynamicTimeSeriesCollection(1, 60, new Second());
        dataset.setTimeBase(new Second(date));
        String titulo = "Uso de " + tipoGrafico;
        dataset.addSeries(gaussianData(), 0, titulo);
        JFreeChart chart = createChart(dataset, tipoGrafico);

        this.add(new ChartPanel(chart), BorderLayout.CENTER);

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware(); //Infos de Hardware do sistema
        CentralProcessor cpu = hal.getProcessor();      //E as informações da cpu
        long[] oldTricks = cpu.getSystemCpuLoadTicks();
        GlobalMemory memory = si.getHardware().getMemory();
        
        timer = new Timer(1000, new ActionListener() {
            float dadoGraf() {
                
               float dados = 0; 
                if (tipoGrafico.equals("RAM")) {
                    long maxRam = memory.getTotal();
                    long availableRam = memory.getAvailable();
                    long usedRam = maxRam - availableRam;
                    double usoRam = ((float) usedRam / (float) maxRam) * 100;
                    double d = usoRam;
                    float f = (float) d;
                    dados = f;
                } else if (tipoGrafico.equals("CPU")) {
                    Double stats = cpu.getSystemCpuLoadBetweenTicks(oldTricks);
                    stats = stats * 100d;
                    double usoCpu = Math.round(stats * 100.0) / 100.0;
                    double d = usoCpu;
                    float f = (float) d;
                    dados = f;
                }return dados;
            }
            
            float[] newData = new float[1];

            @Override
            public void actionPerformed(ActionEvent e) {
                newData[0] = dadoGraf();
                dataset.advanceTime();
                dataset.appendData(newData);
            }
        });
        timer.start();
    }

    private float[] gaussianData() {
        float[] a = new float[COUNT];
        for (int i = 0; i < a.length; i++) {
            a[i] = 2;
        }
        return a;
    }

    private JFreeChart createChart(final XYDataset dataset, String tipo) {
        String titulo = tipo + "%";
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "", "hh:mm:ss", titulo, dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();

        plot.setRangeGridlinePaint(Color.decode("#e8e8e8"));
        plot.setBackgroundPaint(Color.white);
        plot.setOutlinePaint(null);
        plot.setOutlinePaint(null);

        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.decode("#1b6ca8"));

        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setRange(0, MINMAX);
        return result;
    }
}
