package matrix;

import asciiPanel.AsciiPanel;
import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.World;
import com.anish.screen.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 我们想出来的解决办法是造一个MatrixScreen 继承Screen，然后改动main方法即可
 * 现在我们先想办法完善这个代码
 * @author ljh
 * @create 2021-10-11 15:21
 */
public class MatrixScreen implements Screen
{
    private Calabash[][] bros;
    private int maxBros = 20;
    private World world;
    String[] sortSteps;

    //我们需要先写一个方法，得到max*max个元素的一个随机排列
    public MatrixScreen()
    {
        int[] locations = findRandomLocation(maxBros);
        world = new World(30, 30);
        bros = new Calabash[maxBros][maxBros];
        Random rand = new Random(47);
        for (int i = 0; i < maxBros; i++)
        {
            for (int j = 0; j < maxBros; j++)
            {
                bros[i][j] = new Calabash(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)), locations[i * maxBros + j],  world);
            }
        }

        for (int i = 0; i < maxBros; i++)
        {
            for (int j = 0; j < maxBros; j++)
            {
                world.put(bros[i][j], j +5, i + 5);
            }
        }

        BubbleSorter<Calabash> b = new BubbleSorter<>();
        b.load(bros);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[][] bros, String step)
    {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[][] bros, int rank)
    {
        for (Calabash[] broArr : bros)
        {
            for(Calabash bro:broArr)
            {
                if (bro.getRank() == rank)
                {
                    return bro;
                }
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal)
    {
        for (int x = 0; x < World.WIDTH; x++)
        {
            for (int y = 0; y < World.HEIGHT; y++)
            {
                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());
            }
        }
    }
    int i = 0;
    @Override
    public Screen respondToUserInput(KeyEvent key)
    {
        if (i < this.sortSteps.length) {
            this.execute(bros, sortSteps[i]);
            i++;
        }

        return this;
    }
    public static int[] findRandomLocation(int max)
    {
        Random rand = new Random(47);
        int[] result = new int[max * max];
        for (int i = 0; i < max * max; i++)
        {
            result[i] = i;
        }
        for (int i = 0; i < max * max; i++)
        {
            swapReferances(result, i, rand.nextInt(max * max));
        }
        return result;
    }
    public static void swapReferances(int[] targetArray, int first, int second)
    {
        targetArray[first] += targetArray[second];
        targetArray[second] = targetArray[first] - targetArray[second];
        targetArray[first] = targetArray[first] - targetArray[second];
    }
}
