package com.msa.aws.msk.msk_consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MessageReceiver {

    @KafkaListener(topics="Topic_P5_offset_check_1")
    public void receiveMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("PROCESSING START =======================================================" );

        //処理開始時間の表示
        LocalDateTime now_bf = LocalDateTime.now();
        System.out.println("START TIME：" + formatter.format(now_bf)+" & MESSAGE key： "+record.key());
        System.out.println("START TIME：" + formatter.format(now_bf)+" & MESSAGE Value： "+record.value());
        System.out.println("START TIME：" + formatter.format(now_bf)+" & MESSAGE partition： "+record.partition());
        System.out.println("START TIME：" + formatter.format(now_bf)+" & MESSAGE Offset： "+record.offset());

        int waitTime = Integer.parseInt(record.value()) * 1000;
        System.out.println("wait time: " + waitTime);
        waitInMilliseconds(waitTime);
        try {
            acknowledgment.acknowledge(); // コミットを実行
            System.out.println("Commit Success");
        } catch (Exception e) {
            // エラーが発生したらここでスロー
            throw new RuntimeException("Unexpected error while committing record: " + record, e);
        }
        //処理完了時間の表示
        LocalDateTime now_af = LocalDateTime.now();
        System.out.println("END TIME： " + formatter.format(now_af) +" & MESSAGE ID： "+record.key());
        System.out.println("PROCESSING END =======================================================" );
        System.out.println("ALL_INFO======= & MESSAGE Offset： "+record.offset()+ " & MESSAGE partition： "+record.partition()+ " & START_TIME:" + formatter.format(now_bf)+" & MESSAGE Value： "+record.value()+" & END TIME： " + formatter.format(now_af) +" & =======ALL_INFO");


    }
    //数字を受け取って、その時間待機するためのメソッド
    private void waitInMilliseconds(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}
