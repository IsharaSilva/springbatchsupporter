package listner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.springbatchsupporter.listener.JobCompletionNotificationListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;

public class JobCompletionNotificationListenerTest {
	
	private static String JOBINSTANCE="testJob";	
	private static Long ID=1L;
	private static int JOBEXECUTIONTIME=1;
	
	private JobCompletionNotificationListener listener;

	@BeforeEach
    public void setUp() {
        listener = new JobCompletionNotificationListener();
    }
	
    @Test
    public void testBeforeJob() {
        JobExecution jobExecution = mock(JobExecution.class);
        JobInstance jobInstance = new JobInstance(ID, JOBINSTANCE);
        when(jobExecution.getJobInstance()).thenReturn(jobInstance);

        listener.beforeJob(jobExecution);
        verify(jobExecution, times(JOBEXECUTIONTIME)).getJobInstance();
    }

    @Test
    public void testAfterJob() {
        JobExecution jobExecution = mock(JobExecution.class);
        JobInstance jobInstance = new JobInstance(ID, JOBINSTANCE);
        when(jobExecution.getJobInstance()).thenReturn(jobInstance);

        listener.afterJob(jobExecution);
        verify(jobExecution, times(JOBEXECUTIONTIME)).getJobInstance();
    }
}