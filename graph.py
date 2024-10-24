import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Read the CSV file
df = pd.read_csv('results.csv')

# Calculate means for each size
means = df.groupby('Size').mean().reset_index()

# Set style
sns.set_style("whitegrid")
plt.rcParams['figure.figsize'] = [15, 12]

# Create a figure with subplots
fig, ((ax1, ax2), (ax3, ax4)) = plt.subplots(2, 2)
fig.suptitle('Algorithm Comparison: Merge Sort vs Radix Sort', fontsize=16)

# Plot 1: Execution Time Comparison
ax1.plot(means['Size'], means['MergeTime'], marker='o', label='Merge Sort')
ax1.plot(means['Size'], means['RadixTime'], marker='o', label='Radix Sort')
ax1.set_xlabel('Input Size')
ax1.set_ylabel('Time (ms)')
ax1.set_title('Execution Time')
ax1.legend()
ax1.grid(True)
ax1.set_xscale('log')
ax1.set_yscale('log')

# Plot 2: Number of Swaps Comparison
ax2.plot(means['Size'], means['MergeSwaps'], marker='o', label='Merge Sort')
ax2.plot(means['Size'], means['RadixSwaps'], marker='o', label='Radix Sort')
ax2.set_xlabel('Input Size')
ax2.set_ylabel('Number of Swaps')
ax2.set_title('Number of Swaps')
ax2.legend()
ax2.grid(True)
ax2.set_xscale('log')
ax2.set_yscale('log')

# Plot 3: Number of Iterations Comparison
ax3.plot(means['Size'], means['MergeIterations'], marker='o', label='Merge Sort')
ax3.plot(means['Size'], means['RadixIterations'], marker='o', label='Radix Sort')
ax3.set_xlabel('Input Size')
ax3.set_ylabel('Number of Iterations')
ax3.set_title('Number of Iterations')
ax3.legend()
ax3.grid(True)
ax3.set_xscale('log')
ax3.set_yscale('log')

# Plot 4: Box Plot for Time Distribution
time_data = pd.melt(df, id_vars=['Size'], value_vars=['MergeTime', 'RadixTime'],
                    var_name='Algorithm', value_name='Time')
sns.boxplot(x='Size', y='Time', hue='Algorithm', data=time_data, ax=ax4)
ax4.set_xlabel('Input Size')
ax4.set_ylabel('Time (ms)')
ax4.set_title('Time Distribution')
ax4.tick_params(axis='x', rotation=45)

# Adjust layout
plt.tight_layout()

# Save the plot
plt.savefig('sorting_analysis.png', dpi=300, bbox_inches='tight')
plt.close()

print("Plots have been saved as 'sorting_analysis.png'")
