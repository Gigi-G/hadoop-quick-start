package gigig;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class WordCount extends Configured implements Tool {

    private WordCount() {
    }

    public static class TokenizerMapper extends Mapper<Object, Text, Text, LongWritable> {

        private final static Pattern cleaner = Pattern.compile("[^a-z0-9\\s]");

        private final static LongWritable one = new LongWritable(1);

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(cleaner.matcher(value.toString().toLowerCase()).replaceAll(""));
            while (itr.hasMoreTokens()) {
                context.write(new Text(itr.nextToken()), one);
            }
        }
    }

    public static class SumReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

        @Override
        public void reduce(Text key, Iterable<LongWritable> values, Context context)
                throws IOException, InterruptedException {
            long sum = 0;
            for (LongWritable val : values) {
                sum += val.get();
            }
            context.write(key, new LongWritable(sum));
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        // legge la configurazione di hadoop
        Configuration conf = getConf();

        // crea un'istanza del job di map-reduce, c'e' un nuovo job che ha il nome word-count
        Job job = Job.getInstance(conf, "word-count");

        //dove e' presente la classe WordCount saranno presenti anche le altre classi
        job.setJarByClass(WordCount.class);

        // mapper
        job.setMapperClass(TokenizerMapper.class);

        //combiner che puo' usare la reduce perche' fa la stessa cosa sul risultato della map
        job.setCombinerClass(SumReducer.class);

        //reducer
        job.setReducerClass(SumReducer.class);

        // qual e' il formato dell'input (si tratta di un file di testo che viene letto riga per riga)
        job.setInputFormatClass(TextInputFormat.class);

        //  il tipo della chiave nell'output, in questo caso sarà una stringa
        job.setOutputKeyClass(Text.class);

        // il tipo del valore, in questo caso long
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new WordCount(), args);
        System.exit(res);
    }
}





